package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modelo.ClienteM;

public class Cliente extends conexion {

    public void registrar(ClienteM cliente) throws Exception {
        try {
            this.conectar();
            String sql = "INSERT INTO CLIENTE (NOMBRE_CLIE,APELLIDOS_CLIENTE,CELULAR_CLIENTE) VALUES(?,?,?)";
            PreparedStatement ps = this.getCn().prepareStatement(sql);
            ps.setString(1, cliente.getNOMBRE_CLIE());
            ps.setString(2, cliente.getAPELLIDOS_CLIENTE());
            ps.setString(3, cliente.getCELULAR_CLIENTE());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw e;
        } finally {
            this.cerrar();
        }
    }

    public List<ClienteM> listar() throws Exception {
        List<ClienteM> lista;
        ResultSet rs;
        try {
            this.conectar();
            String sql = "SELECT * FROM CLIENTE";
            PreparedStatement ps = this.getCn().prepareCall(sql);
            rs = ps.executeQuery();
            lista = new ArrayList<>();
            while (rs.next()) {
                ClienteM cliente = new ClienteM();
                cliente.setCLIENTE_ID(rs.getString("CLIENTE_ID"));
                cliente.setNOMBRE_CLIE(rs.getString("NOMBRE_CLIE"));
                cliente.setAPELLIDOS_CLIENTE(rs.getString("APELLIDOS_CLIENTE"));
                cliente.setCELULAR_CLIENTE(rs.getString("CELULAR_CLIENTE"));
                lista.add(cliente);
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            this.cerrar();
        }
        return lista;
    }

    public void modificar(ClienteM cliente) throws Exception {
        this.conectar();
        try {
            String sql = "UPDATE CLIENTE SET NOMBRE_CLIE=?,APELLIDOS_CLIENTE=?,CELULAR_CLIENTE=? WHERE CLIENTE_ID=?";
            PreparedStatement ps = this.getCn().prepareStatement(sql);
            ps.setString(1, cliente.getNOMBRE_CLIE());
            ps.setString(2, cliente.getAPELLIDOS_CLIENTE());
            ps.setString(3, cliente.getCELULAR_CLIENTE());
            ps.setString(4, cliente.getCLIENTE_ID());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw e;
        } finally {
            this.cerrar();
        }
    }

    public void eliminar(ClienteM cliente) throws Exception {
        this.conectar();
        try {
            String sql = "DELETE FROM CLIENTE WHERE CLIENTE_ID=?";
            PreparedStatement ps = this.getCn().prepareStatement(sql);
            ps.setString(1, cliente.getCLIENTE_ID());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw e;
        } finally {
            this.cerrar();
        }
    }

    public List<String> autocompleteCliente(String a) throws Exception {
        this.conectar();
        ResultSet rs;
        List<String> Lista;
        try {
            String sql = "SELECT CONCAT(NOMBRE_CLIE,' ',APELLIDOS_CLIENTE) AS CLIENTE\n"
                    + "                   FROM CLIENTE  WHERE UPPER(NOMBRE_CLIE) LIKE UPPER(?);";
            PreparedStatement ps = this.getCn().prepareCall(sql);
            ps.setString(1, "%" + a + "%");
            Lista = new ArrayList<>();
            rs = ps.executeQuery();
            while (rs.next()) {
                Lista.add(rs.getString("CLIENTE"));
            }
            return Lista;
        } catch (SQLException e) {
            throw e;
        } finally {
            this.cerrar();
        }
    }

     public String obtenerCodigoCliente(String a) throws Exception {
        this.conectar();
        ResultSet rs;
        try {
            String sql = "SELECT CLIENTE_ID FROM CLIENTE WHERE UPPER(NOMBRE_CLIE) LIKE UPPER(?)";
            PreparedStatement ps = this.getCn().prepareCall(sql);
            ps.setString(1, a);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getString("CLIENTE_ID");
            }
            return null;
        } catch (SQLException e) {
            throw e;
        } finally {
            this.cerrar();
        }
    }
}
