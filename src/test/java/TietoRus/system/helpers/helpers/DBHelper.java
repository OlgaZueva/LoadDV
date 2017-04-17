package TietoRus.system.helpers.helpers;

import java.sql.*;


public class DBHelper {

     public Connection connToSA() throws SQLException {
        return DriverManager.getConnection("jdbc:sqlserver://10.45.1.122;databaseName=StagingAreaTest;user=sa;password=JGdedf&#fsqwhdc");
    }

    public Connection connToDWH() throws SQLException {
        return DriverManager.getConnection("jdbc:sqlserver://10.45.1.122;databaseName=DataVaultTest;user=sa;password=JGdedf&#fsqwhdc");
    }

    public  Statement stFromConnection(Connection connection) throws SQLException {
        return connection.createStatement();
    }

    public  ResultSet rsFromDB(Statement statement, String sql) throws SQLException {
        return statement.executeQuery(sql);
    }

    public void closeConnecions(ResultSet rs, Statement st, Connection connection) throws SQLException {
        if (rs == null) {
            st.close();
            connection.close();
        } else {
            rs.close();
            st.close();
            connection.close();
        }
    }



}
