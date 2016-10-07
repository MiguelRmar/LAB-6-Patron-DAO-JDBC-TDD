
package edu.eci.pdsw.samples.persistence.jdbcimpl;

//import com.mysql.fabric.proto.xmlrpc.ResultSetParser;
import edu.eci.pdsw.samples.entities.Usuario;
import edu.eci.pdsw.samples.persistence.DaoUsuario;
import edu.eci.pdsw.samples.persistence.PersistenceException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Miguel
 */
public class JDBCDaoUsuario implements DaoUsuario {

    Connection con;

    public JDBCDaoUsuario(Connection con) {
        this.con = con;
    }
        
    @Override
    public Usuario load(String email, String name) throws PersistenceException {
        PreparedStatement ps;
        
        
        try {
            String consulta ="select usu.email, usu.nombre";
            ps=con.prepareCall(consulta);
            ps.setString(1, email);
            ps.setString(2, name);
            ResultSet executeQuery = ps.executeQuery();
            Usuario u=null;
            String e = email;
            String nombre= name;
            u = new Usuario (e, nombre);
            return u;
        } catch (SQLException ex) {
            throw new PersistenceException("An error ocurred while loading "+email,ex);
        }
        }

    @Override
    public void save(Usuario u) throws PersistenceException {
        
        try {
            PreparedStatement ps = null;
            con.setAutoCommit(false);
            String consulta = "INSERT INTO USUARIOS VALUES ( "+ u.getEmail()+", '"+u.getNombre()+"', ?, ?)";
            ps = con.prepareCall(consulta);
            ps.setString(1, u.getEmail());
            ps.setString(2, u.getNombre());
            int res= ps.executeUpdate();
        }
        catch (SQLException e) {
        }
    }

    @Override
    public void update(Usuario u) throws PersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
