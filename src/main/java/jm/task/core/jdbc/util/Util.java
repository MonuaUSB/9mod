package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Util {
    // реализуйте настройку соеденения с БД
    private static final String URL ="jdbc:postgresql://127.0.0.1:5432/ssb3";
    private static final String USER = "postgres";
    private static final String PASSWORD ="123";
    private static SessionFactory sessionFactory;

    public static Connection getConnection(){
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL,USER,PASSWORD);
        } catch (SQLException e){
            e.printStackTrace();
        } return connection;
    }
    public static SessionFactory getSession(){
        try{
            Configuration configuration = new Configuration().addAnnotatedClass(User.class);
            sessionFactory = configuration.buildSessionFactory();
        } catch (Exception e){e.printStackTrace();}
        return sessionFactory;
    }
}
