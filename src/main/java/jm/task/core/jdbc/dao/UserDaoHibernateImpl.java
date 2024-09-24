package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;



public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
        String sql = "CREATE TABLE IF NOT EXISTS users("+
                "id SERIAL PRIMARY KEY, "+
                "name VARCHAR(50), "+
                "lastName VARCHAR(50), "+
                "age SMALLINT)";
        try(Connection conn = Util.getConnection();
            Statement stmt = conn.createStatement()){
            stmt.execute(sql);
            String sql2 = "SELECT * from users";
            stmt.executeQuery(sql2);
        }catch (SQLException e){
            e.printStackTrace();
        }
        System.out.println("Таблица юзеров создана:-)");

    }

    @Override
    public void dropUsersTable() {
        try(Connection conn = Util.getConnection();
            Statement stmt = conn.createStatement()) {
            stmt.execute("Drop table if exists users");
        }catch (SQLException e){
            e.printStackTrace();
        }
        System.out.println("Таблица юзеров уничтожена:-)");

    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Transaction transaction = null;
        try(Session session =Util.getSession().openSession()){
            transaction = session.beginTransaction();
        User user = new User(name,lastName,age);
        session.save(user);
        transaction.commit();
            System.out.println("Юзер с именем: "+name+" добавлен в базу!");
        }catch (Exception e){e.printStackTrace();}
    }

    @Override
    public void removeUserById(long id) {
        Transaction transaction = null;
        try(Session session = Util.getSession().openSession()) {
            transaction=session.beginTransaction();
            User user = session.get(User.class,id);
            if(user!=null){session.delete(user);}
            transaction.commit();
        }

    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        try(
        Session session = Util.getSession().openSession()){
            session.beginTransaction();
             users = session.createQuery("From User",User.class).list();
        } catch (Exception e){e.printStackTrace();}
        return users;
    }

    @Override
    public void cleanUsersTable() {
        Transaction transaction = null;
        try(Session session =Util.getSession().openSession()) {
            transaction=session.beginTransaction();
            session.createQuery("delete from User").executeUpdate();
            transaction.commit();
        }catch (Exception e){e.printStackTrace();}

    }
}
