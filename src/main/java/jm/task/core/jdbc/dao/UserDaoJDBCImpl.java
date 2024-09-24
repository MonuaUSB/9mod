package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {

    }

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

    public void dropUsersTable() {
        try(Connection conn = Util.getConnection();
            Statement stmt = conn.createStatement()) {
            stmt.execute("Drop table if exists users");
        }catch (SQLException e){
            e.printStackTrace();
        }
        System.out.println("Таблица юзеров уничтожена:-)");

    }

    public void saveUser(String name, String lastName, byte age) {
        String sql = "insert into users(name,lastName,age) VALUES (?, ?, ?)";
        try(Connection con = Util.getConnection();
        PreparedStatement st = con.prepareStatement(sql)){
            st.setString(1, name);
            st.setString(2, lastName);
            st.setByte(3, age);
            st.executeUpdate();
            System.out.println("User с именем – " + name + " добавлен в базу данных");
        } catch (SQLException e){
            e.printStackTrace();
        }

    }

    public void removeUserById(long id) {
        try(Connection con = Util.getConnection();
        PreparedStatement pr = con.prepareStatement("DELETE from users where id = ?")) {
            pr.setLong(1,id);
            pr.executeUpdate();
        }catch (SQLException e){e.printStackTrace();}
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        try(Connection con = Util.getConnection();
        Statement st = con.createStatement();
            ResultSet res = st.executeQuery("SELECT * from users")) {
            while (res.next()) {
               User user = new User();
               user.setId(res.getLong("id"));
               user.setAge(res.getByte("age"));
               user.setLastName(res.getString("lastName"));
               user.setName(res.getString("name"));
               users.add(user);
            }
        }catch (SQLException e){e.printStackTrace();}
        return users;
    }

    public void cleanUsersTable() {
        try(Connection con = Util.getConnection();
        Statement st = con.createStatement()){
            st.executeUpdate("DELETE from users");
        }catch (SQLException e){e.printStackTrace();}
    }
}
