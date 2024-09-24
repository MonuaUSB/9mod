package jm.task.core.jdbc;


import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        // реализуйте алгоритм здесь
        UserService userService = new UserServiceImpl();
        userService.createUsersTable();

        userService.saveUser("Денис","Петров",(byte)27);
        userService.saveUser("Глад","Валакас",(byte)54);
        userService.saveUser("Маслори","Реперов",(byte)18);
        userService.saveUser("Ян","Епов",(byte) 25);

        userService.getAllUsers().forEach(System.out::println);

        userService.cleanUsersTable();

        userService.dropUsersTable();
    }
}
