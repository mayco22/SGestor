package model.DAO;

import Connection.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import model.bean.Servico;

/**
 *
 * @author mayco
 */
public class ServicoDAO {
    
    public void create(Servico s){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try {
            
            stmt = con.prepareStatement("INSERT INTO servico (nome_ser) VALUES (?)");
            stmt.setString(1, s.getNome());
            
            stmt.execute();
            JOptionPane.showMessageDialog(null, "Criado com sucesso.");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro:"+e);
        }
        finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
    public List<Servico> read(){
        List<Servico> S = new ArrayList<>();
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt =null;
        ResultSet rs = null; 
        
        try {
            stmt = con.prepareStatement("SELECT * FROM servico");
            rs = stmt.executeQuery();
            
            while(rs.next()){
                Servico s = new Servico();
                s.setNome(rs.getString("nome_ser"));
                s.setId(rs.getInt("id_ser"));
                
                S.add(s);
            }
            return S;
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro:"+e);
        }finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return null;
    }
    public void delete(Servico s){
        PreparedStatement stmt = null;
        Connection con = ConnectionFactory.getConnection();
        try {
            stmt = con.prepareStatement("DELETE FROM servico where id_ser = ?");
            stmt.setInt(1, s.getId());
            
            stmt.execute();
            JOptionPane.showMessageDialog(null, "Deletado com sucesso!");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "erro:"+e);
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
}
