package dao;

import modelo.CanchaM;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Cancha extends conexion {

    public void registrar(CanchaM cancha) throws Exception {
        try {
            this.conectar();
            String sql = "INSERT INTO CANCHA (CANCHA_NOMBRE,CANCHA_TIPO,CANCHA_ESTADO) VALUES(?,?,?)";
            PreparedStatement ps = this.getCn().prepareStatement(sql);
            ps.setString(1, cancha.getCANCHA_NOMBRE());
            ps.setString(2, cancha.getCANCHA_TIPO());
            ps.setString(3, "A");
            ps.executeUpdate();
        } catch (SQLException e) {
            throw e;
        } finally {
            this.cerrar();
        }
    }

    public List<CanchaM> listar() throws Exception {
        List<CanchaM> lista;
        ResultSet rs;
        try {
            this.conectar();
            String sql = "SELECT * FROM CANCHA";
            PreparedStatement ps = this.getCn().prepareCall(sql);
            rs = ps.executeQuery();
            lista = new ArrayList<>();
            while (rs.next()) {
                CanchaM cancha = new CanchaM();
                cancha.setCANCHA_ID(rs.getString("CANCHA_ID"));
                cancha.setCANCHA_NOMBRE(rs.getString("CANCHA_NOMBRE"));
                cancha.setCANCHA_TIPO(rs.getString("CANCHA_TIPO"));
                cancha.setCANCHA_ESTADO(rs.getString("CANCHA_ESTADO"));
                lista.add(cancha);
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            this.cerrar();
        }
        return lista;
    }

    public void modificar(CanchaM cancha) throws Exception {
        this.conectar();
        try {
            String sql = "UPDATE CANCHA SET CANCHA_NOMBRE=?,CANCHA_TIPO=?, CANCHA_ESTADO=? WHERE CANCHA_ID=?";
            PreparedStatement ps = this.getCn().prepareStatement(sql);
            ps.setString(1, cancha.getCANCHA_NOMBRE());
            ps.setString(2, cancha.getCANCHA_TIPO());
            ps.setString(3, cancha.getCANCHA_ESTADO());
            ps.setString(4, cancha.getCANCHA_ID());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw e;
        } finally {
            this.cerrar();
        }
    }

    public void eliminar(CanchaM cancha) throws Exception {
        this.conectar();
        try {
            String sql = "DELETE FROM CANCHA WHERE CANCHA_ID=?";
            PreparedStatement ps = this.getCn().prepareStatement(sql);
            ps.setString(1, cancha.getCANCHA_ID());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw e;
        } finally {
            this.cerrar();
        }
    }
    
      public List<String> autocompleteCancha(String a) throws Exception {
        this.conectar();
        ResultSet rs;
        List<String> Lista;
        try {
            String sql = "SELECT CONCAT(CANCHA_NOMBRE,' / ',CANCHA_TIPO) AS CANCHA\n" +
"                   FROM CANCHA  WHERE UPPER(CANCHA_NOMBRE) LIKE UPPER(?);";
            PreparedStatement ps = this.getCn().prepareCall(sql);
            ps.setString(1, "%" + a + "%");
            Lista = new ArrayList<>();
            rs = ps.executeQuery();
            while (rs.next()) {
                Lista.add(rs.getString("CANCHA"));
            }
            return Lista;
        } catch (SQLException e) {
            throw e;
        } finally {
            this.cerrar();
        }
    }

    public String obtenerCodigoCancha(String a) throws Exception {
        this.conectar();
        ResultSet rs;
        try {
            String sql = "SELECT CANCHA_ID FROM CANCHA WHERE UPPER(CANCHA_TIPO) LIKE UPPER(?)";
            PreparedStatement ps = this.getCn().prepareCall(sql);
            ps.setString(1, a);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getString("CANCHA_ID");
            }
            return null;
        } catch (SQLException e) {
            throw e;
        } finally {
            this.cerrar();
        }
    }
}
