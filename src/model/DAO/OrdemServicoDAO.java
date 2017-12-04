package model.DAO;

import Connection.ConnectionSQlServer;
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
    
    public void create(Ordemservico o) throws SQLException{
        Connection con = ConnectionSQlServer.getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = con.prepareStatement("INSERT INTO OrdemServico(valor,id_cli_or,id_ser_or,descricao)VALUES(?,?,?,?)");
            stmt.setDouble(1, o.getValor());
            stmt.setInt(2, o.getCli().getId());
            stmt.setInt(3, o.getSer().getId());
            stmt.setString(4, o.getDescricao());
            stmt.execute();
            
            JOptionPane.showMessageDialog(null, "Gravado com sucesso.");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "erro:"+e);
        }finally{
            ConnectionSQlServer.closeConnection(con, stmt);
        }
    }
    public List<Ordemservico> Read() throws SQLException{
        List<Ordemservico> l = new ArrayList<>();
        Cliente c = new Cliente();
        Servico s = new Servico();
        Connection con = ConnectionSQlServer.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ClienteDAO cd = new ClienteDAO();
        ServicoDAO sd = new ServicoDAO();
        
        try {
            stmt = con.prepareStatement("SELECT * FROM OrdemServico WHERE status_or = 1");
            rs = stmt.executeQuery();
            
            while(rs.next()){
                Ordemservico o = new Ordemservico();
                o.setId(rs.getInt("id_or"));
                o.setDate(rs.getString("dataservico"));
                for (Cliente cs : cd.read()) {
                    if (rs.getInt("id_cli_or") == cs.getId()) {
                        c = cs;
                    }
                }
                o.setCli(c);
                for (Servico ss : sd.read()) {
                    if (rs.getInt("id_ser_or") == ss.getId()) {
                        s = ss;
                    }
                }
                o.setSer(s);
                o.setValor(rs.getDouble("valor"));
                l.add(o);
            }
            return l;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "erro:"+e);
        }
        finally{
            ConnectionSQlServer.closeConnection(con, stmt, rs);
        }
        return null;
    }
    public List<Ordemservico> Read2() throws SQLException{
        List<Ordemservico> l = new ArrayList<>();
        Cliente c = new Cliente();
        Servico s = new Servico();
        Connection con = ConnectionSQlServer.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ClienteDAO cd = new ClienteDAO();
        ServicoDAO sd = new ServicoDAO();
        
        try {
            stmt = con.prepareStatement("select * from OrdemServico where month(dataservico) = month(GETDATE()) and status_or = 0 ");
            rs = stmt.executeQuery();
            
            while(rs.next()){
                Ordemservico o = new Ordemservico();
                o.setId(rs.getInt("id_or"));
                o.setDate(rs.getString("dataservico"));
                for (Cliente cs : cd.read()) {
                    if (rs.getInt("id_cli_or") == cs.getId()) {
                        c = cs;
                    }
                }
                o.setCli(c);
                for (Servico ss : sd.read()) {
                    if (rs.getInt("id_ser_or") == ss.getId()) {
                        s = ss;
                    }
                }
                o.setSer(s);
                o.setValor(rs.getDouble("valor"));
                l.add(o);
            }
            return l;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "erro:"+e);
        }
        finally{
            ConnectionSQlServer.closeConnection(con, stmt, rs);
        }
        return null;
    }
    public List<Ordemservico> Read3(String a,String b) throws SQLException{
        List<Ordemservico> l = new ArrayList<>();
        Cliente c = new Cliente();
        Servico s = new Servico();
        Connection con = ConnectionSQlServer.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ClienteDAO cd = new ClienteDAO();
        ServicoDAO sd = new ServicoDAO();
        
        try {
            stmt = con.prepareStatement("SELECT * FROM OrdemServico WHERE dataservico BETWEEN ? AND ? ORDER BY dataservico");
            stmt.setString(1, a);
            stmt.setString(2, b);
            
            rs = stmt.executeQuery();
            
            while(rs.next()){
                Ordemservico o = new Ordemservico();
                o.setId(rs.getInt("id_or"));
                o.setDate(rs.getString("dataservico"));
                for (Cliente cs : cd.read()) {
                    if (rs.getInt("id_cli_or") == cs.getId()) {
                        c = cs;
                    }
                }
                o.setCli(c);
                for (Servico ss : sd.read()) {
                    if (rs.getInt("id_ser_or") == ss.getId()) {
                        s = ss;
                    }
                }
                o.setSer(s);
                o.setValor(rs.getDouble("valor"));
                l.add(o);
            }
            return l;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "erro:"+e);
        }
        finally{
            ConnectionSQlServer.closeConnection(con, stmt, rs);
        }
        return null;
    }
     public List<Ordemservico> Read4(int Ordem) throws SQLException{
        List<Ordemservico> l = new ArrayList<>();
        Cliente c = new Cliente();
        Servico s = new Servico();
        Connection con = ConnectionSQlServer.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ClienteDAO cd = new ClienteDAO();
        ServicoDAO sd = new ServicoDAO();
        
        try {
            stmt = con.prepareStatement("select o.id_or,c.nome_cli,s.nome_ser,o.valor,o.dataservico,o.status_or from ordemservico o\n" +
                                        "join Cliente c on  c.id_cli = o.id_cli_or\n" +
                                        "join servico s on s.id_ser = o.id_ser_or\n" +
                                        "where id_or = ?");
            stmt.setInt(1, Ordem);
            
            rs = stmt.executeQuery();
            
            while(rs.next()){
                Ordemservico o = new Ordemservico();
                o.setId(rs.getInt("id_or"));
                o.setDate(rs.getString("dataservico"));
                c.setNome(rs.getString("nome_cli"));
                o.setCli(c);
                s.setNome(rs.getString("nome_ser"));
                o.setSer(s);
                o.setValor(rs.getDouble("valor"));
                o.setStatus(rs.getInt("status_or"));
                
                l.add(o);
            }
            return l;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "erro:"+e);
        }
        finally{
            ConnectionSQlServer.closeConnection(con, stmt, rs);
        }
        return null;
    }
    public void update(String descricao,int id) throws SQLException{
        Connection con = ConnectionSQlServer.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("update ordemservico set descricao = ? where id_or = ?");
            stmt.setString(1, descricao);
            stmt.setInt(2, id);
            stmt.execute();
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro:"+e);
        }finally{
            ConnectionSQlServer.closeConnection(con, stmt);
        }
    }
    
}
