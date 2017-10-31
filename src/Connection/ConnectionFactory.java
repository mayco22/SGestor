package Connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author mayco
 */
public class ConnectionFactory {
    
    private static Connection con = null;
    public static Connection getConnection(){
        try {
            con = DriverManager.getConnection("jdbc:sqlite:SGestor_DB.db");
            return con;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro: "+e);
            return null;
        }
    }
    public static void closeConnection(Connection con){
        try{
            if(con != null){
                con.close();
            }
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Erro: "+e);
        }
    }
    public static void closeConnection(Connection con,PreparedStatement stmt){
        try{
            if(stmt != null){
                stmt.close();
            }
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Erro: "+e);
        }
        closeConnection(con);
    }
    public static void closeConnection(Connection con,PreparedStatement stmt,ResultSet rs){
        try{
            if(rs != null){
                rs.close();
            }
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Erro: "+e);
        }
        closeConnection(con,stmt);
    }
}
