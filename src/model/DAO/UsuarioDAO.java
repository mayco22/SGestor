package model.DAO;

import java.sql.Connection;
import Connection.ConnectionFactory;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;
/**
 *
 * @author mayco
 */
public class UsuarioDAO {
    
    public void busca(){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("SELECT * FROM usu WHERE nome = ?");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
        finally{
            ConnectionFactory.closeConnection(con,stmt);
        }
    }
}
