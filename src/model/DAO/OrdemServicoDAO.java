package model.DAO;

import Connection.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import model.bean.Ordemservico;

/**
 *
 * @author mayco
 */
public class OrdemServicoDAO {
    
    public void create(Ordemservico o){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = con.prepareStatement("INSERT INTO ");
            stmt.setInt(0, o.getId());
            stmt.execute();
            
        } catch (SQLException e) {
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
}
