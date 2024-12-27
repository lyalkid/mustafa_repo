package ru.itis.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Stack;

public class ConnectionManager {
    private static final String DB_USERNAME = "postgres";
    private static final String DB_PASSWORD = "postgres";
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/mustafa";
    private static final String DB_DRIVER = "org.postgresql.Driver";
    private static final int DB_POOL_SIZE = 10;
    private static Stack<Connection> connectionPool = new Stack<>();


    // загрузка драйвера в память
    static {
       loadDriver();
       initConnectionPool();
   }
    public ConnectionManager(){}

    private static void initConnectionPool(){
        for(int i = 0; i < DB_POOL_SIZE; i++){
            connectionPool.push(openConnection());
        }
    }
    private static void loadDriver() {
        try {
            Class.forName(DB_DRIVER);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public Connection get(){
        return connectionPool.pop();
    }

    public void putConnection(Connection connection){
        connectionPool.push(connection);
    }

    private static Connection openConnection() {
        try {
            return DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
        }
        catch (SQLException ex){
            throw new RuntimeException(ex);
        }
    }

    public void destroyConnection() {
        while (!connectionPool.isEmpty()) {
            try {
                Connection connection = connectionPool.pop();
                if (connection != null && !connection.isClosed()) {
                    connection.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

    }
}
