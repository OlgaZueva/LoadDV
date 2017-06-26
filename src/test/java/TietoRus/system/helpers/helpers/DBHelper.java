package TietoRus.system.helpers.helpers;

import java.sql.*;


public class DBHelper {

     public Connection connToSA() throws SQLException {

      //return DriverManager.getConnection("jdbc:sqlserver://10.45.1.122;databaseName=StagingAreaTest;user=ssis;password=ssis");
        //return DriverManager.getConnection("jdbc:sqlserver://10.21.11.15\\SQL2016;databaseName=StagingAreaTest;user=ssis;password=ssis");
         //return DriverManager.getConnection("jdbc:sqlserver://10.21.11.15\\SQL2016;databaseName=StagingAreaSmallTest;user=ssis;password=ssis");
       //return DriverManager.getConnection("jdbc:sqlserver://10.21.11.11;databaseName=StagingAreaTest;user=ssis;password=ssis");
         return DriverManager.getConnection("jdbc:sqlserver://10.21.11.11;databaseName=StagingAreaTestCDC;user=ssis;password=ssis");

    }

    public Connection connToDWH() throws SQLException {
        //return DriverManager.getConnection("jdbc:sqlserver://10.45.1.122;databaseName=DataVaultTest;user=ssis;password=ssis");
       //return DriverManager.getConnection("jdbc:sqlserver://10.21.11.15\\SQL2016;databaseName=DataVaultTest;user=ssis;password=ssis");
      //  return DriverManager.getConnection("jdbc:sqlserver://10.21.11.15\\SQL2016;databaseName=DataVaultSmallTest;user=ssis;password=ssis");
       // return DriverManager.getConnection("jdbc:sqlserver://10.21.11.11;databaseName=DataVaultTest;user=ssis;password=ssis");
       return DriverManager.getConnection("jdbc:sqlserver://10.21.11.11;databaseName=DataVaultTestCDC;user=ssis;password=ssis");

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
