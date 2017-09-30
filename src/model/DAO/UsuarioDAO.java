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
    
    public List<Usuario> read(){
        Connection con = ConnectionFactory.getConnection();
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
            ConnectionFactory.closeConnection(con,stmt,rs);
        }
    }
    public void create(String nome, String senha, String perfil){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        try{
            stmt = con.prepareStatement("INSERT INTO usuario(nome,senha,perfil) VALUES (?,?,?)");
            stmt.setString(1, nome);
            stmt.setString(2, senha);
            stmt.setString(3, perfil);
            
            stmt.execute();
            JOptionPane.showMessageDialog(null, "Criado com Sucesso!");
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
        finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
    public void update(int id,String nome, String senha, String perfil){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("UPDATE usuario set nome=?,senha=?,perfil=? WHERE id =?");
            stmt.setString(1, nome);
            stmt.setString(2, senha);
            stmt.setString(3, perfil);
            stmt.setInt(4, id);
            
            stmt.execute();
            JOptionPane.showMessageDialog(null, "Alterado Com sucesso.");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "erro:"+e);
        }
        finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
    public void delete(int id){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = con.prepareStatement("delete from usuario where id = ?");
            stmt.setInt(1, id);
            
            stmt.execute();
            JOptionPane.showMessageDialog(null, "Deletado com sucesso.");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERRO:"+e);
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
        
    }
}
