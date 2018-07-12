/**
 * 
 */
package model.dao.khoi;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author Fresher001
 *
 */
public final class ConnectDB {
    private Connection connect = null;
    public void openConnection(){
        try {
            Class.forName("net.sourceforge.jtds.jdbc.Driver");
            if(connect == null || connect.isClosed())
                connect = DriverManager.getConnection("jdbc:jtds:sqlserver://localhost:1433/EDUProject","longnt","longnt");
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }         
    }
    public void closeConnection(){
        try {
            if(connect != null || (!connect.isClosed()))
                connect.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    /**
     * It return 1 connection.
     * @return the connect
     */
    public Connection getConnect() {
        return connect;
    }
}
