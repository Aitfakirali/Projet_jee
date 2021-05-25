package com.demo.dao;

import com.demo.beans.Etudiant;
import com.demo.beans.Livre;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DaoFactory {
    final static String DRIVER = "com.mysql.cj.jdbc.Driver";
    final static String URL = "jdbc:mysql://localhost:3306/db_biblio";
    final static String USER = "root";
    final static String PASS = "root";

    private static DaoFactory instance = null;

    public static DaoFactory getInstance() {
        if(instance == null) {
            instance = new DaoFactory();
            try {
                Class.forName(DRIVER);
            } catch ( ClassNotFoundException e ) {

            }
        }
        return instance;
    }


    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASS);
    }

    public Dao<Etudiant> getEtudianDaoImpl(){
        return new EtudiantDaoManager(this);
    }

    public Dao<Livre> getLivreDaoImpl(){
        return new LivreDaoManager(this);
    }

}
