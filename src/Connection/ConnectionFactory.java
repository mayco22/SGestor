package Connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author mayco
 */
public class ConnectionFactory {
    
    private static Connection con = null;
    public static Connection getConnection(){
        try {
            String url ="jdbc:sqlite:SGestor_DB.db";
            con = DriverManager.getConnection(url);
            System.out.println("Conexao Estabelecida");
            return con;
            
        } catch (SQLException e) {
            System.err.println(e);
            return null;
        }
    }
    public static void closeConnection(Connection con){
        try{
            if(con != null){
                con.close();
            }
        }catch(SQLException e){
            System.err.println(e);
        }
    }
    public static void closeConnection(Connection con,PreparedStatement stmt){
        try{
            if(stmt != null){
                stmt.close();
            }
        }catch(SQLException e){
            System.err.println(e);
        }
        closeConnection(con);
    }
    public static void closeConnection(Connection con,PreparedStatement stmt,ResultSet rs){
        try{
            if(rs != null){
                rs.close();
            }
        }catch(SQLException e){
            System.err.println(e);
        }
        closeConnection(con,stmt);
    }
}
