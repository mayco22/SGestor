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
public class ConnectionSQlServer {
    
    public static Connection getConnection() throws SQLException{
        try {  
  
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");  
        Connection con = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=Sgestor_DB;user=sa;password=sa");  
        return con;  
  
        }catch (ClassNotFoundException e) {  
           throw new SQLException(e.getMessage());  
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
