package model.DAO;

import java.sql.Connection;
import Connection.ConnectionFactory;
import Connection.ConnectionSQlServer;
import java.awt.HeadlessException;
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
    
    public List<Usuario> read() throws SQLException{
        Connection con = ConnectionSQlServer.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs  = null;
        List<Usuario> u = new ArrayList<>(); 
        
        try {
            
            stmt = con.prepareStatement("SELECT * FROM usuario");
            rs = stmt.executeQuery();
            while(rs.next()){
                Usuario usuario = new Usuario();
                usuario.setId(rs.getInt("id"));
                usuario.setNome(rs.getString("nome"));
                usuario.setSenha(rs.getString("senha"));
                usuario.setPerfil(rs.getString("perfil"));
                
                u.add(usuario);
            }
            return u;
            
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally{
            ConnectionSQlServer.closeConnection(con,stmt,rs);
        }
    }
    public void create(Usuario u) throws SQLException{
        Connection con = ConnectionSQlServer.getConnection();
        PreparedStatement stmt = null;
        try{
            stmt = con.prepareStatement("INSERT INTO usuario(nome,senha,perfil) VALUES (?,?,?)");
            stmt.setString(1, u.getNome());
            stmt.setString(2, u.getSenha());
            stmt.setString(3, u.getPerfil());
            
            stmt.execute();
            JOptionPane.showMessageDialog(null, "Criado com Sucesso!");
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
        finally{
            ConnectionSQlServer.closeConnection(con, stmt);
        }
    }
    public void update(Usuario u) throws SQLException{
        Connection con = ConnectionSQlServer.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("UPDATE usuario set nome=?,senha=?,perfil=? WHERE id =?");
            stmt.setString(1, u.getNome());
            stmt.setString(2, u.getSenha());
            stmt.setString(3, u.getPerfil());
            stmt.setInt(4, u.getId());
            
            stmt.execute();
            JOptionPane.showMessageDialog(null, "Alterado Com sucesso.");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "erro:"+e);
        }
        finally{
            ConnectionSQlServer.closeConnection(con, stmt);
        }
    }
    public void delete(int id) throws SQLException{
        Connection con = ConnectionSQlServer.getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = con.prepareStatement("delete from usuario where id = ?");
            stmt.setInt(1, id);
            
            stmt.execute();
            JOptionPane.showMessageDialog(null, "Deletado com sucesso.");
        } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(null, "ERRO:"+e);
        }finally{
            ConnectionSQlServer.closeConnection(con, stmt);
        }
        
    }
    public int autenticacao(String nome,String senha) throws SQLException{
        Connection con = ConnectionSQlServer.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        int l=0;
        try {
            stmt = con.prepareStatement("select dbo.fc_aut(?,?)");
            stmt.setString(1, nome);
            stmt.setString(2, senha);
            rs = stmt.executeQuery();
            while(rs.next()){
                l = rs.getInt("");
            }
            return l;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro: "+e);
        }finally{
            ConnectionSQlServer.closeConnection(con, stmt, rs);
        }
        return 0;
    }
}
