package model.DAO;

import Connection.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import model.bean.Cliente;
import model.bean.Ordemservico;
import model.bean.Servico;

/**
 *
 * @author mayco
 */
public class OrdemServicoDAO {
    
    public void create(Ordemservico o){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = con.prepareStatement("INSERT INTO OrdemServico(dataservico,valor,id_cli_or,id_ser_or) VALUES(?,?,?,?)");
            stmt.setString(1, o.getDate());
            stmt.setDouble(2, o.getValor());
            stmt.setInt(3, o.getCli().getId());
            stmt.setInt(4, o.getSer().getId());
            stmt.execute();
            
            JOptionPane.showMessageDialog(null, "Gravado com sucesso.");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "erro:"+e);
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
    public List<Ordemservico> Read(){
        List<Ordemservico> l = new ArrayList<>();
        Cliente c = new Cliente();
        Servico s = new Servico();
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            stmt = con.prepareStatement("SELECT * FROM OrdemServico");
            rs = stmt.executeQuery();
            
            while(rs.next()){
                Ordemservico o = new Ordemservico();
                o.setId(rs.getInt("id_or"));
                o.setDate(rs.getString("dataservico"));
                c.setId(rs.getInt("id_cli_or"));
                o.setCli(c);
                s.setId(rs.getInt("id_ser_or"));
                o.setSer(s);
                o.setValor(rs.getDouble("valor"));
                l.add(o);
            }
            return l;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "erro:"+e);
        }
        finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return null;
    }
}
