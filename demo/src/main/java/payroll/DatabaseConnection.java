package payroll;

import java.sql.*;

public class DatabaseConnection {
    private String url= "jdbc:mysql://localhost:3306/payroll";
    private String username = "root";
    private String password = "Tuboneurale15!";
    private Connection connection;
    private Statement st;

    private Connection ConnectToDatabase() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        this.connection = DriverManager.getConnection(url, username, password);
        return connection;
    }

    public ResultSet ExecuteQuery(String sqlStatement) throws SQLException, ClassNotFoundException {
        Statement st = ConnectToDatabase().createStatement();
        return st.executeQuery(sqlStatement);
    }

    public PreparedStatement QueryUpdate(String sqlStatement) throws SQLException, ClassNotFoundException {
        PreparedStatement ps = ConnectToDatabase().prepareStatement(sqlStatement);
        return ps;
    }

    public void CloseConnection() throws SQLException {
        if(!connection.isClosed()){
            connection.close();
        }
    }
}
