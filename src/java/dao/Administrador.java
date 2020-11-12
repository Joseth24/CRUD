package dao;

import modelo.AdministradorM;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Administrador extends conexion{

    public void registrar(AdministradorM admin) throws Exception {
        try {
            this.conectar();
            String sql = "INSERT INTO ADMINISTRADOR (TRABAJADOR_NOMBRE,TRABAJADOR_APELLIDO,TRABAJADOR_DNI,TRABAJADOR_DIRECCION,TRABAJADOR_CELULAR,TRABAJADOR_TIPO,TRABAJADOR_USUARIO,TRABAJADOR_PASS) VALUES(?,?,?,?,?,?,?,?)";
            PreparedStatement ps = this.getCn().prepareStatement(sql);
            ps.setString(1, admin.getTRABAJADOR_NOMBRE());
            ps.setString(2, admin.getTRABAJADOR_APELLIDO());
            ps.setString(3, admin.getTRABAJADOR_DNI());
            ps.setString(4, admin.getTRABAJADOR_DIRECCION());
            ps.setString(5, admin.getTRABAJADOR_CELULAR());
            ps.setString(6, admin.getTRABAJADOR_TIPO());
            ps.setString(7, admin.getTRABAJADOR_USUARIO());
            ps.setString(8, admin.getTRABAJADOR_PASS());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw e;
        } finally {
            this.cerrar();
        }
    }

    public List<AdministradorM> listar() throws Exception {
        List<AdministradorM> lista;
        ResultSet rs;
        try {
            this.conectar();
            String sql = "SELECT * FROM ADMINISTRADOR";
            PreparedStatement ps = this.getCn().prepareCall(sql);
            rs = ps.executeQuery();
            lista = new ArrayList<>();
            while (rs.next()) {
                AdministradorM admin = new AdministradorM();
                admin.setTRABAJADOR_ID(rs.getString("TRABAJADOR_ID"));
                admin.setTRABAJADOR_NOMBRE(rs.getString("TRABAJADOR_NOMBRE"));
                admin.setTRABAJADOR_APELLIDO(rs.getString("TRABAJADOR_APELLIDO"));
                admin.setTRABAJADOR_DNI(rs.getString("TRABAJADOR_DNI"));
                admin.setTRABAJADOR_DIRECCION(rs.getString("TRABAJADOR_DIRECCION"));
                admin.setTRABAJADOR_CELULAR(rs.getString("TRABAJADOR_CELULAR"));
                admin.setTRABAJADOR_TIPO(rs.getString("TRABAJADOR_TIPO"));
                admin.setTRABAJADOR_USUARIO(rs.getString("TRABAJADOR_USUARIO"));
                admin.setTRABAJADOR_PASS(rs.getString("TRABAJADOR_PASS"));
                lista.add(admin);
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            this.cerrar();
        }
        return lista;
    }

    public void modificar(AdministradorM admin) throws Exception {
        this.conectar();
        try {
            String sql = "UPDATE ADMINISTRADOR SET TRABAJADOR_NOMBRE=?,TRABAJADOR_APELLIDO=?, TRABAJADOR_DNI=?,TRABAJADOR_DIRECCION=?,TRABAJADOR_CELULAR=?,TRABAJADOR_USUARIO=?,TRABAJADOR_PASS=? WHERE TRABAJADOR_ID=?";
            PreparedStatement ps = this.getCn().prepareStatement(sql);
            ps.setString(1, admin.getTRABAJADOR_NOMBRE());
            ps.setString(2, admin.getTRABAJADOR_APELLIDO());
            ps.setString(3, admin.getTRABAJADOR_DNI());
            ps.setString(4, admin.getTRABAJADOR_DIRECCION());
            ps.setString(5, admin.getTRABAJADOR_CELULAR());
            ps.setString(6, admin.getTRABAJADOR_USUARIO());
            ps.setString(7, admin.getTRABAJADOR_PASS());
            ps.setString(8, admin.getTRABAJADOR_ID());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw e;
        } finally {
            this.cerrar();
        }
    }

    public void eliminar(AdministradorM admin) throws Exception {
        this.conectar();
        try {
            String sql = "DELETE FROM ADMINISTRADOR WHERE TRABAJADOR_ID=?";
            PreparedStatement ps = this.getCn().prepareStatement(sql);
            ps.setString(1, admin.getTRABAJADOR_ID());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw e;
        } finally {
            this.cerrar();
        }
    }
}
