package model.DAO;

import java.sql.Connection;
import Connection.ConnectionFactory;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import model.bean.Usuario;
/**
 *
 * @author mayco
 */
public class UsuarioDAO {
    
    public List<Usuario> busca(){
        
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs  = null;
        List<Usuario> usuarios = new ArrayList<Usuario>(); 
        
        try {
            
            stmt = con.prepareStatement("SELECT * FROM usu");
            rs = stmt.executeQuery();
            while(rs.next()){
                Usuario usuario = new Usuario();
                usuario.setId(rs.getInt("id"));
                usuario.setNome(rs.getString("nome"));
                usuario.setSenha(rs.getString("senha"));
                
                usuarios.add(usuario);
            }
            return usuarios;
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,"erro:"+ e);
            return null;
        }
        finally{
            ConnectionFactory.closeConnection(con,stmt,rs);
        }
    }
}
