package model.DAO;

import java.sql.Connection;
import Connection.ConnectionFactory;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.bean.Cliente;

/**
 *
 * @author mayco
 */
public class ClienteDAO {
    Connection con = ConnectionFactory.getConnection();
    
    public void Create(){
        PreparedStatement stmt = null;
        Cliente c = new Cliente();
        try {
            stmt = con.prepareStatement("INSERT INTO Cliente(nome_cli,email_cli,telefone_cli,celular_cli) VALUES (?,?,?,?)");
            stmt.setString(1, c.getNome());
            stmt.setString(2, c.getEmail());
            stmt.setString(3, c.getTelefone());
            stmt.setString(4, c.getCelular());
            
            stmt.execute();
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
    public void update(int id,String nome,String email,String telefone,String celular){
        PreparedStatement stmt = null;
        Cliente c = new Cliente();
        
        try {
            stmt = con.prepareStatement("UPDATE Cliente SET nome_cli=?,email_cli=?,telefone_cli=?,celular_cli=? WHERE id_cli = ?");
            stmt.setString(1, nome);
            stmt.setString(2, email);
            stmt.setString(3, telefone);
            stmt.setString(4, celular);
            stmt.setInt(5, id);
            
            stmt.execute();
        } catch (SQLException e) {
            System.err.println(e);
        }
        finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
    public List<Cliente> read(){
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
            throw new RuntimeException(e);
        }finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
    }
    public void delete(){
        
    }
    
}
