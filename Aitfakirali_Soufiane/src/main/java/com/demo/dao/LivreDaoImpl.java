package com.demo.dao;

import com.demo.beans.Category;
import com.demo.beans.Etudiant;
import com.demo.beans.Livre;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class LivreDaoImpl implements Dao<Livre>{
    DaoFactory factory = null;

    public LivreDaoImpl(DaoFactory factory){
        this.factory = factory;
    }

    @Override
    public Livre getOneByid(int id) {
    	String sql = "SELECT *FROM livre WHERE livre_id="+id;
    	Livre lv = null;
        Connection con = null;
        Statement st = null;
        ResultSet res = null;
        try {
            con = factory.getConnection();
            st = con.createStatement();
            res = st.executeQuery(sql);
            if(res.next()) {
                lv = new Livre();
                lv.setLivre_id(res.getInt("livre_id"));
                lv.setLivre_titre(res.getString("livre_titre"));
                lv.setLivre_auteur(res.getString("livre_auteur"));
                lv.setImageUrl(res.getString("livre_imageUrl"));
                lv.setDescription(res.getString("description"));
                lv.setDate_creation(res.getDate("date_creation"));
                lv.setCategory(getCategory(res.getInt("categ_id")));
                lv.setEmprunte(isEmprunt(lv.getLivre_id()));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            Close(con,st,res);
        }
        return lv;
    }

    @Override
    public List<Livre> getAll() {
    	String sql = "SELECT *FROM livre";
        List<Livre> Livres = new ArrayList<Livre>();
        Connection con = null;
        Statement st = null;
        ResultSet res = null;
        try {
            con = factory.getConnection();
            st = con.createStatement();
            res = st.executeQuery(sql);
            while(res.next()) {
            	Livre lv = new Livre();
                 lv.setLivre_id(res.getInt("livre_id"));
                 lv.setLivre_titre(res.getString("livre_titre"));
                 lv.setLivre_auteur(res.getString("livre_auteur"));
                 lv.setImageUrl(res.getString("livre_imageUrl"));
                 lv.setDescription(res.getString("description"));
                 lv.setDate_creation(res.getDate("date_creation"));
                 lv.setCategory(getCategory(res.getInt("categ_id")));
                 lv.setEmprunte(isEmprunt(lv.getLivre_id()));
                 Livres.add(lv);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            Close(con,st,res);
        }
        return Livres;
    }
    
    public Category getCategory(int id){
    	String sql = "SELECT *FROM category WHERE category_id="+id;
        Connection con = null;
        Statement st = null;
        ResultSet res = null;
        try {
            con = factory.getConnection();
            st = con.createStatement();
            res = st.executeQuery(sql);
            if(res.next()) {
            	Category cat = new Category();
            	cat.setCategory_id(res.getInt("category_id"));
            	cat.setCategory_nom(res.getString("category_nom"));
            	cat.setDate_update(res.getDate("date_update"));
                return cat;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
        	Close(con,st,res);
        }
    	return null;
    }
    boolean isEmprunt(int id) {
    	String sql = "SELECT *FROM emprunt WHERE livre_id="+id;
        Connection con = null;
        Statement st = null;
        ResultSet res = null;
        try {
            con = factory.getConnection();
            st = con.createStatement();
            res = st.executeQuery(sql);
            if(res.next()) {
            	return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
        	Close(con,st,res);
        }
    	return false;
    }

    @Override
    public boolean Update(Livre o) {
        return false;
    }

    @Override
    public boolean Delete(int id) {
    	String sql = "DELETE FROM livre where livre_id=?";
    	Connection con = null;
    	PreparedStatement st = null;
		try {
			con = factory.getConnection();
			st = con.prepareStatement(sql);
			st.setInt(1, id);
	    	st.executeUpdate();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			Close(con,st,null);
		}
    	return false;
    }

    @Override
    public boolean Save(Livre o) {
    	String sql = "INSERT INTO `db_biblio`.`livre` (`livre_auteur`, `livre_titre`, `date_creation`, `categ_id`) "
    			+ "VALUES (?, ?, ?, ?)";
    	Connection con = null;
    	PreparedStatement st = null;
		try {
			con = factory.getConnection();
			st = con.prepareStatement(sql);
			st.setString(1, o.getLivre_auteur());
			st.setString(2, o.getLivre_titre());
			st.setDate(3, o.getDate_creation());
			st.setInt(4,o.getCategory().getCategory_id());
	    	if(st.executeUpdate() != 0)
	    		return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			Close(con,st,null);
		}
        return false;
    }
    
    @Override
  	public void Close(Connection con,Statement st,ResultSet res){
          if(con != null) {
              try {
                  con.close();
              } catch (SQLException e) {
                  e.printStackTrace();
              }
          }
          if(st != null) {
              try {
                  st.close();
              } catch (SQLException e) {
                  e.printStackTrace();
              }
          }
          if(res != null) {
              try {
                  res.close();
              } catch (SQLException e) {
                  e.printStackTrace();
              }
          }
      }
    
    public List<Category> getCategories(){
    	String sql ="SELECT *FROM category";
    	List<Category> categories = new ArrayList<Category>();
        Connection con = null;
        Statement st = null;
        ResultSet res = null;
        try {
            con = factory.getConnection();
            st = con.createStatement();
            res = st.executeQuery(sql);
            while(res.next()) {
            	Category cat = new Category();
            	cat.setCategory_id(res.getInt("category_id"));
            	cat.setCategory_nom(res.getString("category_nom"));
            	cat.setDate_update(res.getDate("date_update"));
            	categories.add(cat);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            Close(con,st,res);
        }
        
    	return categories;
    }

	public List<Livre> getLivres_Disponible() {
		String sql = "SELECT *\r\n"
				+ "FROM livre\r\n"
				+ "WHERE NOT EXISTS (SELECT * FROM emprunt WHERE emprunt.livre_id = livre.livre_id);";
        List<Livre> Livres = new ArrayList<Livre>();
        Connection con = null;
        Statement st = null;
        ResultSet res = null;
        try {
            con = factory.getConnection();
            st = con.createStatement();
            res = st.executeQuery(sql);
            while(res.next()) {
            	Livre lv = new Livre();
                 lv.setLivre_id(res.getInt("livre_id"));
                 lv.setLivre_titre(res.getString("livre_titre"));
                 lv.setLivre_auteur(res.getString("livre_auteur"));
                 lv.setImageUrl(res.getString("livre_imageUrl"));
                 lv.setDate_creation(res.getDate("date_creation"));
                 lv.setDescription(res.getString("description"));
                 lv.setCategory(getCategory(res.getInt("categ_id")));
                 Livres.add(lv);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            Close(con,st,res);
        }
        return Livres;
	}

	public boolean emprunte(int etudiant_id, int livre_id) {
		String sql = "INSERT INTO emprunt values(?,?,NOW(),NOW());";
    	Connection con = null;
    	PreparedStatement st = null;
		try {
			con = factory.getConnection();
			st = con.prepareStatement(sql);
			st.setInt(1, etudiant_id);
			st.setInt(2, livre_id);
	    	if(st.executeUpdate() != 0)
	    		return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			Close(con,st,null);
		}
    	return false;
		
		
	}

	public boolean retour(int id) {
		String sql = "DELETE FROM emprunt where livre_id=?";
    	Connection con = null;
    	PreparedStatement st = null;
		try {
			con = factory.getConnection();
			st = con.prepareStatement(sql);
			st.setInt(1, id);
	    	st.executeUpdate();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			Close(con,st,null);
		}
    	return false;
		
	}
}
