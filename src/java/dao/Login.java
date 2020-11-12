package dao;

import modelo.AdministradorM;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Login extends conexion {

    public AdministradorM IniciarSesion(String User, String Passw) throws Exception {
        this.conectar();
        ResultSet rs;
        AdministradorM admin = null;
        try {
            String sql = "SELECT TRABAJADOR_ID,TRABAJADOR_NOMBRE,TRABAJADOR_TIPO FROM ADMINISTRADOR WHERE TRABAJADOR_USUARIO LIKE ? AND TRABAJADOR_PASS LIKE ?";
            PreparedStatement ps = this.getCn().prepareCall(sql);
            ps.setString(1, User);
            ps.setString(2, Passw);
            rs = ps.executeQuery();
            if (rs.next()) {
                admin = new AdministradorM();
                admin.setTRABAJADOR_ID(rs.getString("TRABAJADOR_ID"));
                admin.setTRABAJADOR_NOMBRE(rs.getString("TRABAJADOR_NOMBRE"));
                admin.setTRABAJADOR_TIPO(rs.getString("TRABAJADOR_TIPO"));
                admin.setTRABAJADOR_USUARIO(User);
                admin.setTRABAJADOR_PASS(Passw);
            }
            return admin;
        } catch (SQLException e) {
            throw e;
        } finally {
            this.cerrar();
        }
    }
}
