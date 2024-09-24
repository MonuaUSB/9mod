package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;

import java.util.List;

public class UserServiceImpl implements UserService {
    private final UserDao userDao = new UserDaoJDBCImpl();
    private final UserDao userhiber = new UserDaoHibernateImpl();
    public void createUsersTable() {
//        userDao.createUsersTable();
        userhiber.createUsersTable();

    }

    public void dropUsersTable() {
//        userDao.dropUsersTable();
        userhiber.dropUsersTable();

    }

    public void saveUser(String name, String lastName, byte age) {
//        userDao.saveUser(name,lastName,age);
        userhiber.saveUser(name,lastName,age);

    }

    public void removeUserById(long id) {
//        userDao.removeUserById(id);
        userhiber.removeUserById(id);

    }

    public List<User> getAllUsers() {
//        return userDao.getAllUsers();
        return userhiber.getAllUsers();
    }

    public void cleanUsersTable() {
//        userDao.cleanUsersTable();
        userhiber.cleanUsersTable();

    }
}
