package com.demo.dao;

import com.demo.beans.Etudiant;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class EtudiantDaoImpl implements Dao<Etudiant>{
    DaoFactory factory = null;

    public EtudiantDaoImpl(DaoFactory factory){
        this.factory = factory;
    }
    
    // Workiiing
    @Override
    public Etudiant getOneByid(int id) {
        String sql = "SELECT *FROM etudiant WHERE etudiant_id="+id;
        Etudiant et = null;
        Connection con = null;
        Statement st = null;
        ResultSet res = null;
        try {
            con = factory.getConnection();
            st = con.createStatement();
            res = st.executeQuery(sql);
            if(res.next()) {
                et = new Etudiant();
                et.setEtudiant_id(res.getInt("etudiant_id"));
                et.setEtudiant_nom(res.getString("etudiant_nom"));
                et.setEtudiant_prenom(res.getString("etudiant_prenom"));
                et.setEtudiant_age(res.getInt("etudiant_age"));
                et.setEtudiant_email(res.getString("etudiant_email"));
                et.setEtudiant_password(res.getString("etudiant_password"));
                et.setEtudiant_telephone(res.getString("etudiant_telephone"));
                et.setEtudiant_adresse(res.getString("etudiant_adresse"));
                et.setEtudiant_filiere(res.getString("etudiant_filiere"));
                et.setEtudiant_role(getRole(res.getInt("etudiant_role")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
        	Close(con,st,res);
        }
        return et;
    }
    
    public boolean isExist(String email){
    	String sql = "SELECT * FROM etudiant WHERE etudiant_email=?";
        Connection con = null;
        PreparedStatement st = null;
        ResultSet res = null;
        try {
            con = factory.getConnection();
            st = con.prepareStatement(sql);
            st.setString(1, email);
            res = st.executeQuery();
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
    
    //Workiing
    public String getRole(int id) {
    	String sql = "SELECT *FROM roles WHERE role_id="+id;
        Connection con = null;
        Statement st = null;
        ResultSet res = null;
        try {
            con = factory.getConnection();
            st = con.createStatement();
            res = st.executeQuery(sql);
            if(res.next()) {
                return res.getString("role_nom");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
        	Close(con,st,res);
        }
    	return null;
    }
    
    public Etudiant getEtudiantReserveLivre(int id){
    	 String sql = "SELECT *FROM etudiant WHERE etudiant_id = ANY(SELECT etud_id from emprunt WHERE livre_id = ?);";
         Etudiant et = null;
         Connection con = null;
         PreparedStatement st = null;
         ResultSet res = null;
         try {
             con = factory.getConnection();
             st = con.prepareStatement(sql);
             st.setInt(1, id);
             res = st.executeQuery();
             if(res.next()) {
                 et = new Etudiant();
                 et.setEtudiant_id(res.getInt("etudiant_id"));
                 et.setEtudiant_nom(res.getString("etudiant_nom"));
                 et.setEtudiant_prenom(res.getString("etudiant_prenom"));
                 et.setEtudiant_age(res.getInt("etudiant_age"));
                 et.setEtudiant_email(res.getString("etudiant_email"));
                 et.setEtudiant_password(res.getString("etudiant_password"));
                 et.setEtudiant_telephone(res.getString("etudiant_telephone"));
                 et.setEtudiant_adresse(res.getString("etudiant_adresse"));
                 et.setEtudiant_filiere(res.getString("etudiant_filiere"));
                 et.setEtudiant_role(getRole(res.getInt("etudiant_role")));
             }
         } catch (SQLException e) {
             e.printStackTrace();
         }finally {
         	Close(con,st,res);
         }
         return et;
    }
    
    //Workiing
    public Etudiant Auth(String email,String pass) {
    	String sql = "SELECT etudiant_id FROM etudiant WHERE etudiant_email=?";
        Etudiant et = null;
        Connection con = null;
        PreparedStatement st = null;
        ResultSet res = null;
        try {
            con = factory.getConnection();
            st = con.prepareStatement(sql);
            st.setString(1, email);
            res = st.executeQuery();
            if(res.next()) {
            	if(isPasswordCorrect(pass)) {
            		et = this.getOneByid(res.getInt("etudiant_id"));
            	}
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
        	Close(con,st,res);
        }
        return et;
    }
    
    //Workiing
    private boolean isPasswordCorrect(String pass) {
    	String sql = "SELECT * FROM etudiant WHERE etudiant_password=MD5(?)";
        Connection con = null;
        PreparedStatement st = null;
        ResultSet res = null;
        try {
            con = factory.getConnection();
            st = con.prepareStatement(sql);
            st.setString(1, pass);
            res = st.executeQuery();
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
    public List<Etudiant> getAll() {
    	 String sql = "SELECT *FROM etudiant;";
         List<Etudiant> etudiants = new ArrayList<Etudiant>();
         Connection con = null;
         Statement st = null;
         ResultSet res = null;
         try {
             con = factory.getConnection();
             st = con.createStatement();
             res = st.executeQuery(sql);
             while(res.next()) {
            	 Etudiant et = new Etudiant();
                 et.setEtudiant_id(res.getInt("etudiant_id"));
                 et.setEtudiant_nom(res.getString("etudiant_nom"));
                 et.setEtudiant_prenom(res.getString("etudiant_prenom"));
                 et.setEtudiant_age(res.getInt("etudiant_age"));
                 et.setEtudiant_email(res.getString("etudiant_email"));
                 et.setEtudiant_password(res.getString("etudiant_password"));
                 et.setEtudiant_telephone(res.getString("etudiant_telephone"));
                 et.setEtudiant_adresse(res.getString("etudiant_adresse"));
                 et.setEtudiant_filiere(res.getString("etudiant_filiere"));
                 et.setEtudiant_role(getRole(res.getInt("etudiant_role")));
                 etudiants.add(et);
             }
         } catch (SQLException e) {
             e.printStackTrace();
         }finally {
             Close(con,st,res);
         }
         return etudiants;
    }
    
    public boolean ChangeRole(int id,int role_id) {
    	String sql ="UPDATE `db_biblio`.`etudiant` SET `etudiant_role` = ? WHERE (`etudiant_id` = ?);";
    	Connection con = null;
    	PreparedStatement st = null;
		try {
			con = factory.getConnection();
			st = con.prepareStatement(sql);
			st.setInt(1,role_id);
			st.setInt(2,id);
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
    public boolean Update(Etudiant o) {
    	String sql = "UPDATE etudiant"
    			+ " SET etudiant_nom = ?, etudiant_prenom = ?, etudiant_email = ?, etudiant_adresse = ?, etudiant_telephone = ?, etudiant_filiere = ?, etudiant_age = ?"
    			+ " WHERE etudiant_id = ?";   	
    	Connection con = null;
    	PreparedStatement st = null;
		try {
			con = factory.getConnection();
			st = con.prepareStatement(sql);
			st.setString(1, o.getEtudiant_nom());
			st.setString(2, o.getEtudiant_prenom());
			st.setString(3, o.getEtudiant_email());
			st.setString(4, o.getEtudiant_adresse());
			st.setString(5, o.getEtudiant_telephone());
			st.setString(6, o.getEtudiant_filiere());
			st.setInt(7, o.getEtudiant_age());
			st.setInt(8, o.getEtudiant_id());
			if(st.executeUpdate() != 0)
				return true;
		} catch (SQLException e) {
			return false;
		} finally {
			Close(con,st,null);
		}
		return false;
    }

    @Override
    public boolean Delete(int id) {
    	String sql = "DELETE FROM etudiant where etudiant_id=?";
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
    public boolean Save(Etudiant o) {
    	String sql = "INSERT INTO etudiant "
    			+ "( etudiant_nom, etudiant_prenom, etudiant_email, etudiant_adresse, etudiant_telephone, etudiant_password, etudiant_filiere, etudiant_role,etudiant_age) "
    			+ "VALUES (?, ?, ?, ?, ?, MD5(?), ?,2,?);";   	
    	Connection con = null;
    	PreparedStatement st = null;
		try {
			con = factory.getConnection();
			st = con.prepareStatement(sql);
			st.setString(1, o.getEtudiant_nom());
			st.setString(2, o.getEtudiant_prenom());
			st.setString(3, o.getEtudiant_email());
			st.setString(4, o.getEtudiant_adresse());
			st.setString(5, o.getEtudiant_telephone());
			st.setString(6, o.getEtudiant_password());
			st.setString(7, o.getEtudiant_filiere());
			st.setInt(8, o.getEtudiant_age());
			if(st.executeUpdate() != 0)
				return true;
		} catch (SQLException e) {
			return false;
		} finally {
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

}
