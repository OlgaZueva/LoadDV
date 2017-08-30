package TietoRus.system.helpers.helpers;

import java.sql.*;


public class DBHelper {

    public Connection connToITest() throws SQLException {
        return DriverManager.getConnection("jdbc:oracle:thin:dwh_etl/T4M2iJfRGw@10.45.101.220:1551:ITEST");
    }

    public Connection connToRTest() throws SQLException {
        return DriverManager.getConnection("jdbc:oracle:thin:dwh_etl/T4M2iJfRGw@10.45.1.223:1566:RTEST");
    }

    public Connection connToSA() throws SQLException {

       // return DriverManager.getConnection("jdbc:sqlserver://10.45.1.122;databaseName=StagingAreaTest;user=ssis;password=ssis");
       // return DriverManager.getConnection("jdbc:sqlserver://10.21.11.15;databaseName=StagingAreaTest;user=ssis;password=ssis");
     // return DriverManager.getConnection("jdbc:sqlserver://10.21.11.11;databaseName=StagingAreaTest;user=ssis;password=ssis");
        return DriverManager.getConnection("jdbc:sqlserver://10.45.101.96;databaseName=StagingAreaTest;user=sa;password=JGdedf&#fsqwhdc");


    }

    public Connection connToDWH() throws SQLException {
       //return DriverManager.getConnection("jdbc:sqlserver://10.45.1.122;databaseName=DataVaultTest;user=ssis;password=ssis");
       // return DriverManager.getConnection("jdbc:sqlserver://10.21.11.15;databaseName=DataVaultTest;user=ssis;password=ssis");
       //return DriverManager.getConnection("jdbc:sqlserver://10.21.11.11;databaseName=DataVaultTest;user=ssis;password=ssis");
        return DriverManager.getConnection("jdbc:sqlserver://10.45.101.96;databaseName=DataVaultTest;user=sa;password=JGdedf&#fsqwhdc");

    }

    public Statement stFromConnection(Connection connection) throws SQLException {
        return connection.createStatement();
    }

    public ResultSet rsFromDB(Statement statement, String sql) throws SQLException {
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
