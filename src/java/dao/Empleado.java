package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modelo.EmpleadoM;

public class Empleado extends conexion {

    public void registrar(EmpleadoM empleado) throws Exception {
        try {
            this.conectar();
            String sql = "INSERT INTO EMPLEADO (EMPLEADO_NOMBRE,EMPLEADO_APELLIDO,EMPLEADO_DNI,EMPLEADO_DIRECCION,EMPLEADO_CELULAR,EMPLEADO_TIPO,EMPLEADO_USUARIO,EMPLEADO_PASS) VALUES(?,?,?,?,?,?,?,?)";
            PreparedStatement ps = this.getCn().prepareStatement(sql);
            ps.setString(1, empleado.getEMPLEADO_NOMBRE());
            ps.setString(2, empleado.getEMPLEADO_APELLIDO());
            ps.setString(3, empleado.getEMPLEADO_DNI());
            ps.setString(4, empleado.getEMPLEADO_DIRECCION());
            ps.setString(5, empleado.getEMPLEADO_CELULAR());
            ps.setString(6, "E");
            ps.setString(7, "empleado");
            ps.setString(8, "empleado");
            ps.executeUpdate();
        } catch (SQLException e) {
            throw e;
        } finally {
            this.cerrar();
        }
    }

    public List<EmpleadoM> listar() throws Exception {
        List<EmpleadoM> lista;
        ResultSet rs;
        try {
            this.conectar();
            String sql = "SELECT * FROM EMPLEADO";
            PreparedStatement ps = this.getCn().prepareCall(sql);
            rs = ps.executeQuery();
            lista = new ArrayList<>();
            while (rs.next()) {
                EmpleadoM empleado = new EmpleadoM();
                empleado.setEMPLEADO_ID(rs.getString("EMPLEADO_ID"));
                empleado.setEMPLEADO_NOMBRE(rs.getString("EMPLEADO_NOMBRE"));
                empleado.setEMPLEADO_APELLIDO(rs.getString("EMPLEADO_APELLIDO"));
                empleado.setEMPLEADO_DNI(rs.getString("EMPLEADO_DNI"));
                empleado.setEMPLEADO_DIRECCION(rs.getString("EMPLEADO_DIRECCION"));
                empleado.setEMPLEADO_CELULAR(rs.getString("EMPLEADO_CELULAR"));
                empleado.setEMPLEADO_TIPO(rs.getString("EMPLEADO_TIPO"));
                empleado.setEMPLEADO_USUARIO(rs.getString("EMPLEADO_USUARIO"));
                empleado.setEMPLEADO_PASS(rs.getString("EMPLEADO_PASS"));
                lista.add(empleado);
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            this.cerrar();
        }
        return lista;
    }

    public void modificar(EmpleadoM empleado) throws Exception {
        this.conectar();
        try {
            String sql = "UPDATE ADMINISTRADOR SET TRABAJADOR_NOMBRE=?,TRABAJADOR_APELLIDO=?, TRABAJADOR_DNI=?,TRABAJADOR_DIRECCION=?,TRABAJADOR_CELULAR=?,TRABAJADOR_USUARIO=?,TRABAJADOR_PASS=? WHERE TRABAJADOR_ID=?";
            PreparedStatement ps = this.getCn().prepareStatement(sql);
            ps.setString(1, empleado.getEMPLEADO_NOMBRE());
            ps.setString(2, empleado.getEMPLEADO_APELLIDO());
            ps.setString(3, empleado.getEMPLEADO_DNI());
            ps.setString(4, empleado.getEMPLEADO_DIRECCION());
            ps.setString(5, empleado.getEMPLEADO_CELULAR());
            ps.setString(6, empleado.getEMPLEADO_ID());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw e;
        } finally {
            this.cerrar();
        }
    }

    public void eliminar(EmpleadoM admin) throws Exception {
        this.conectar();
        try {
            String sql = "DELETE FROM ADMINISTRADOR WHERE TRABAJADOR_ID=?";
            PreparedStatement ps = this.getCn().prepareStatement(sql);
            ps.setString(1, admin.getEMPLEADO_ID());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw e;
        } finally {
            this.cerrar();
        }
    }

    public List<String> autocompleteEmpleado(String a) throws Exception {
        this.conectar();
        ResultSet rs;
        List<String> Lista;
        try {
            String sql = "SELECT CONCAT(EMPLEADO_NOMBRE,' ',EMPLEADO_APELLIDO) AS EMPLEADO\n"
                    + "                   FROM EMPLEADO  WHERE UPPER(EMPLEADO_NOMBRE) LIKE UPPER(?)";
            PreparedStatement ps = this.getCn().prepareCall(sql);
            ps.setString(1, "%" + a + "%");
            Lista = new ArrayList<>();
            rs = ps.executeQuery();
            while (rs.next()) {
                Lista.add(rs.getString("EMPLEADO"));
            }
            return Lista;
        } catch (SQLException e) {
            throw e;
        } finally {
            this.cerrar();
        }
    }

    public String obtenerCodigoEmpleado(String a) throws Exception {
        this.conectar();
        ResultSet rs;
        try {
            String sql = "SELECT EMPLEADO_ID FROM EMPLEADO WHERE UPPER(EMPLEADO_NOMBRE) LIKE UPPER(?)";
            PreparedStatement ps = this.getCn().prepareCall(sql);
            ps.setString(1, a);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getString("EMPLEADO_ID");
            }
            return null;
        } catch (SQLException e) {
            throw e;
        } finally {
            this.cerrar();
        }
    }
}
