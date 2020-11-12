package dao;

import modelo.ProductoM;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Producto extends conexion {

    public void registrar(ProductoM producto) throws Exception {
        try {
            this.conectar();
            String sql = "INSERT INTO PRODUCTO (PRODUCTO_TIPO,PRODUCTO_PRECIO,PROVEEDOR_PROVEEDOR_ID,PRODUCTO_DESCRIPCION) VALUES(?,?,?,?)";
            PreparedStatement ps = this.getCn().prepareStatement(sql);
            ps.setString(1, producto.getPRODUCTO_TIPO());
            ps.setString(2, producto.getPRODUCTO_PRECIO());
            ps.setString(3, producto.getPROVEEDOR_PROVEEDOR_ID());
            ps.setString(4, producto.getPRODUCTO_DESCRIPCION());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw e;
        } finally {
            this.cerrar();
        }
    }

    public void modificar(ProductoM producto) throws Exception {
        try {
            this.conectar();
            String sql = "UPDATE PRODUCTO SET PRODUCTO_TIPO=?,PRODUCTO_PRECIO=?, PROVEEDOR_PROVEEDOR_ID=?,PRODUCTO_DESCRIPCION=? WHERE PRODUCTO_ID=?";
            PreparedStatement ps = this.getCn().prepareStatement(sql);
            ps.setString(1, producto.getPRODUCTO_TIPO());
            ps.setString(2, producto.getPRODUCTO_PRECIO());
            ps.setString(3, producto.getPROVEEDOR_PROVEEDOR_ID());
            ps.setString(4, producto.getPRODUCTO_DESCRIPCION());
            ps.setString(5, producto.getPRODUCTO_ID());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw e;
        } finally {
            this.cerrar();
        }
    }

    public List<ProductoM> listar() throws Exception {
        List<ProductoM> lista;
        ResultSet rs;
        try {
            this.conectar();
            String sql = "SELECT PRODUCTO_ID,PRODUCTO_TIPO,PRODUCTO_PRECIO,PROVEEDOR_NOMBRE AS PROVEEDOR, PRODUCTO_DESCRIPCION\n"
                    + "FROM PRODUCTO\n"
                    + "INNER JOIN PROVEEDOR ON PRODUCTO.PROVEEDOR_PROVEEDOR_ID = PROVEEDOR.PROVEEDOR_ID;";
            PreparedStatement ps = this.getCn().prepareCall(sql);
            rs = ps.executeQuery();
            lista = new ArrayList<>();
            while (rs.next()) {
                ProductoM producto = new ProductoM();
                producto.setPRODUCTO_ID(rs.getString("PRODUCTO_ID"));
                producto.setPRODUCTO_TIPO(rs.getString("PRODUCTO_TIPO"));
                producto.setPRODUCTO_PRECIO(rs.getString("PRODUCTO_PRECIO"));
                producto.setPROVEEDOR_PROVEEDOR_ID(rs.getString("PROVEEDOR"));
                producto.setPRODUCTO_DESCRIPCION(rs.getString("PRODUCTO_DESCRIPCION"));
                lista.add(producto);
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            this.cerrar();
        }
        return lista;
    }

    public void eliminar(ProductoM producto) throws Exception {
        this.conectar();
        try {
            String sql = "DELETE FROM PRODUCTO WHERE PRODUCTO_ID=?";
            PreparedStatement ps = this.getCn().prepareStatement(sql);
            ps.setString(1, producto.getPRODUCTO_ID());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw e;
        } finally {
            this.cerrar();
        }
    }

    public List<String> autocompleteProducto(String a) throws Exception {
        this.conectar();
        ResultSet rs;
        List<String> Lista;
        try {
            String sql = "SELECT CONCAT(PRODUCTO_TIPO,' / ',PRODUCTO_DESCRIPCION,' / ',PRODUCTO_PRECIO) AS PRODUCTO_TIPO\n"
                    + "                   FROM PRODUCTO  WHERE UPPER(PRODUCTO_TIPO) LIKE UPPER(?)";
            PreparedStatement ps = this.getCn().prepareCall(sql);
            ps.setString(1, "%" + a + "%");
            Lista = new ArrayList<>();
            rs = ps.executeQuery();
            while (rs.next()) {
                Lista.add(rs.getString("PRODUCTO_TIPO"));
            }
            return Lista;
        } catch (SQLException e) {
            throw e;
        } finally {
            this.cerrar();
        }
    }

    public String obtenerCodigoProducto(String a) throws Exception {
        this.conectar();
        ResultSet rs;
        try {
            String sql = "SELECT PRODUCTO_ID FROM PRODUCTO WHERE UPPER(PRODUCTO_TIPO) LIKE UPPER(?)";
            PreparedStatement ps = this.getCn().prepareCall(sql);
            ps.setString(1, a);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getString("PRODUCTO_ID");
            }
            return null;
        } catch (SQLException e) {
            throw e;
        } finally {
            this.cerrar();
        }
    }
}
