package uz.pdp;

import java.sql.*;

public class DatabaseService {

    private final String url = "jdbc:postgresql://localhost:5432/app-jdbc-example";
    private final String dbUser = "postgres";
    private final String dbPassword = "Stormshadow007";

    public void saveUser(User user) {

        try {
            Connection boglovchi = DriverManager.getConnection(url, dbUser, dbPassword);
            Statement statement = boglovchi.createStatement();
            String query = "insert into users(first_name, last_name, user_name, password)" +
                    "values('" + user.getFirstName() + "','" + user.getLastName() + "','" + user.getUserName() + "','" + user.getPassword() + "');";
            statement.execute(query);
            System.out.println("User added");

            statement.close();
            boglovchi.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public void getUsers() throws SQLException {

        Connection boglovchi = DriverManager.getConnection(url, dbUser, dbPassword);
        Statement statement = boglovchi.createStatement();

        String query = "select * from users";
        ResultSet resultSet = statement.executeQuery(query);
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String name = resultSet.getString("first_name");
            String lastname = resultSet.getString("last_name");
            String username = resultSet.getString("user_name");
            String pass = resultSet.getString("password");

            User user = new User(id, name, lastname, username, pass);
            System.out.println(user);
        }
        statement.close();
        boglovchi.close();
    }

    public void deleteUser(int userId) {

        try {
            Connection connection = DriverManager.getConnection(url, dbUser, dbPassword);
            Statement statement = connection.createStatement();
            String query = "delete from users where id=" + userId;
            boolean execute = statement.execute(query);
            System.out.println("User deleted");

            statement.close();
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void editUser(User user) {
        try {
            Connection connection = DriverManager.getConnection(url, dbUser, dbPassword);
            Statement statement = connection.createStatement();
            String query = "update users set ";
            if (!user.getFirstName().isEmpty()) {
                query = query + "first_name='" + user.getFirstName() + "', ";
            }
            if (!user.getLastName().isEmpty()) {
                query = query + "last_name='" + user.getLastName() + "', ";
            }
            if (!user.getUserName().isEmpty()) {
                query = query + "user_name='" + user.getUserName() + "', ";
            }
            if (!user.getPassword().isEmpty()) {
                query = query + "password='" + user.getPassword() + "'";
            }
            if (!query.equals("update users set ")) {
                if (query.endsWith(",'")) {
                    query = query.substring(0, query.length() - 1);
                }
                query = query + " where id=" + user.getId();
                statement.execute(query);
                System.out.println("User edited");
            }

            statement.close();
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void saveUserPreparedStatement(User user) {

        try {
            Connection boglovchi = DriverManager.getConnection(url, dbUser, dbPassword);
            String query = "insert into users(first_name, last_name, user_name, password) values(?, ?, ?, ?);";
            PreparedStatement preparedStatement = boglovchi.prepareStatement(query);
            preparedStatement.setString(1, user.getFirstName());
            preparedStatement.setString(2, user.getLastName());
            preparedStatement.setString(3, user.getUserName());
            preparedStatement.setString(4, user.getPassword());
            preparedStatement.executeUpdate();
            System.out.println("User added by preparedStatement");

            preparedStatement.close();
            boglovchi.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

}
