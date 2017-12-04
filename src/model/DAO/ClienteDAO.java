package model.DAO;

import java.sql.Connection;
import Connection.ConnectionSQlServer;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import model.bean.Cliente;

/**
 *
 * @author mayco
 */
public class ClienteDAO {
    
    
    public void Create(Cliente c ) throws SQLException{
        PreparedStatement stmt = null;
        Connection con = ConnectionSQlServer.getConnection();
        try {
            stmt = con.prepareStatement("INSERT INTO Cliente(nome_cli,email_cli,telefone_cli,celular_cli) VALUES (?,?,?,?)");
            stmt.setString(1, c.getNome());
            stmt.setString(2, c.getEmail());
            stmt.setString(3, c.getTelefone());
            stmt.setString(4, c.getCelular());
            
            stmt.execute();
            JOptionPane.showMessageDialog(null, "Criado com Sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "erro:"+ex);
        }
        finally{
            ConnectionSQlServer.closeConnection(con, stmt);
        }
    }
    public void update(Cliente c) throws SQLException{
        PreparedStatement stmt = null;
        Connection con = ConnectionSQlServer.getConnection();
        try {
            stmt = con.prepareStatement("UPDATE Cliente SET nome_cli=?,email_cli=?,telefone_cli=?,celular_cli=? WHERE id_cli = ?");
            stmt.setString(1, c.getNome());
            stmt.setString(2, c.getEmail());
            stmt.setString(3, c.getTelefone());
            stmt.setString(4, c.getCelular());
            stmt.setInt(5, c.getId());
            
            stmt.execute();
            JOptionPane.showMessageDialog(null, "Atualizado com Sucesso!");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "erro:"+e);        
        }
        finally{
            ConnectionSQlServer.closeConnection(con, stmt);
        }
    }
    public List<Cliente> read() throws SQLException{
        Connection con = ConnectionSQlServer.getConnection();
        List<Cliente> l = new ArrayList<>();
        PreparedStatement stmt =null;
        ResultSet rs = null;
        
        try {
            stmt = con.prepareStatement("SELECT * FROM Cliente");
            rs = stmt.executeQuery();
            
            while(rs.next()){
                Cliente c = new Cliente();
                c.setId(rs.getInt("id_cli"));
                c.setNome(rs.getString("nome_cli"));
                c.setEmail(rs.getString("email_cli"));
                c.setTelefone(rs.getString("telefone_cli"));
                c.setCelular(rs.getString("celular_cli"));
                
                l.add(c);
            }
            return l;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "erro:"+e);
        }finally{
            ConnectionSQlServer.closeConnection(con, stmt, rs);
        }
        return null;
    }
    public void delete(Cliente c) throws SQLException{
        PreparedStatement stmt= null;
        Connection con = ConnectionSQlServer.getConnection();
        try {
            stmt = con.prepareStatement("DELETE FROM Cliente WHERE id_cli =?");
            stmt.setInt(1, c.getId());
            
            stmt.execute();
            JOptionPane.showMessageDialog(null, "Deletado com Sucesso!");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro: "+e);
        }finally{
            ConnectionSQlServer.closeConnection(con, stmt);
        }
    }
    
}
