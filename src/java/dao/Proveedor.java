package dao;

import modelo.ProveedorM;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Proveedor extends conexion {

    public void registrar(ProveedorM proveedor) throws Exception {
        try {
            this.conectar();
            String sql = "INSERT INTO PROVEEDOR (PROVEEDOR_NOMBRE,PROVEEDOR_CELULAR,PROVEEDOR_RUC) VALUES(?,?,?)";
            PreparedStatement ps = this.getCn().prepareStatement(sql);
            ps.setString(1, proveedor.getPROVEEDOR_NOMBRE());
            ps.setString(2, proveedor.getPROVEEDOR_CELULAR());
            ps.setString(3, proveedor.getPROVEEDOR_RUC());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw e;
        } finally {
            this.cerrar();
        }
    }

    public void modificar(ProveedorM proveedor) throws Exception {
        try {
            this.conectar();
            String sql = "UPDATE PROVEEDOR SET PROVEEDOR_NOMBRE=?,PROVEEDOR_CELULAR=?, PROVEEDOR_RUC=? WHERE PROVEEDOR_ID=?";
            PreparedStatement ps = this.getCn().prepareStatement(sql);
            ps.setString(1, proveedor.getPROVEEDOR_NOMBRE());
            ps.setString(2, proveedor.getPROVEEDOR_CELULAR());
            ps.setString(3, proveedor.getPROVEEDOR_RUC());
            ps.setString(4, proveedor.getPROVEEDOR_ID());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw e;
        } finally {
            this.cerrar();
        }
    }

    public List<ProveedorM> listar() throws Exception {
        List<ProveedorM> lista;
        ResultSet rs;
        try {
            this.conectar();
            String sql = "SELECT * FROM PROVEEDOR";
            PreparedStatement ps = this.getCn().prepareCall(sql);
            rs = ps.executeQuery();
            lista = new ArrayList<>();
            while (rs.next()) {
                ProveedorM proveedor = new ProveedorM();
                proveedor.setPROVEEDOR_ID(rs.getString("PROVEEDOR_ID"));
                proveedor.setPROVEEDOR_NOMBRE(rs.getString("PROVEEDOR_NOMBRE"));
                proveedor.setPROVEEDOR_CELULAR(rs.getString("PROVEEDOR_CELULAR"));
                proveedor.setPROVEEDOR_RUC(rs.getString("PROVEEDOR_RUC"));
                lista.add(proveedor);
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            this.cerrar();
        }
        return lista;
    }

    public void eliminar(ProveedorM proveedor) throws Exception {
        this.conectar();
        try {
            String sql = "DELETE FROM PROVEEDOR WHERE PROVEEDOR_ID=?";
            PreparedStatement ps = this.getCn().prepareStatement(sql);
            ps.setString(1, proveedor.getPROVEEDOR_ID());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw e;
        } finally {
            this.cerrar();
        }
    }

    public List<String> autocompleteProveedor(String a) throws Exception {
        this.conectar();
        ResultSet rs;
        List<String> Lista;
        try {
            String sql = "SELECT UPPER(PROVEEDOR_NOMBRE) AS PROVEEDOR_NOMBRE\n"
                    + "                 FROM PROVEEDOR\n"
                    + "                    WHERE UPPER(PROVEEDOR_NOMBRE) LIKE UPPER(?)";
            PreparedStatement ps = this.getCn().prepareCall(sql);
            ps.setString(1, "%" + a + "%");
            Lista = new ArrayList<>();
            rs = ps.executeQuery();
            while (rs.next()) {
                Lista.add(rs.getString("PROVEEDOR_NOMBRE"));
            }
            return Lista;
        } catch (SQLException e) {
            throw e;
        } finally {
            this.cerrar();
        }
    }

    public String obtenerCodigoProveedor(String a) throws Exception {
        this.conectar();
        ResultSet rs;
        try {
            String sql = "SELECT PROVEEDOR_ID FROM PROVEEDOR WHERE UPPER(PROVEEDOR_NOMBRE) LIKE UPPER(?)";
            PreparedStatement ps = this.getCn().prepareCall(sql);
            ps.setString(1, a);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getString("PROVEEDOR_ID");
            }
            return null;
        } catch (SQLException e) {
            throw e;
        } finally {
            this.cerrar();
        }
    }
}
