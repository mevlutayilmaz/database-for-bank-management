
package metotlar;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;


public class DBbaglanti {
    Connection c = null;
    
    public DBbaglanti(){}
    
    public Connection conDB() throws ClassNotFoundException{
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            this.c = DriverManager.getConnection("jdbc:sqlserver://DESKTOP-5726RJU;database=Bank;integratedSecurity=true");
            return c;
        } catch (SQLException ex) {
            Logger.getLogger(DBbaglanti.class.getName()).log(Level.SEVERE, null, ex);
        }
        return c;
    }
 
}
