package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modelo.ReservaM;

public class Reserva extends conexion {

    public void registrar(ReservaM reserva) throws Exception {
        try {
            this.conectar();
            String sql = "INSERT INTO RESERVA (RESERVA_HORA_INICIO,RESERVA_HORA_FIN,RESERVA_DIA,CLIENTE_CLIENTE_ID,RESERVA_PAGO,RESERVA_MONTO,EMPLEADO_EMPLEADO_ID,PRODUCTO_PRODUCTO_ID,CANCHA_CANCHA_ID,PRODUCTO_CANTIDAD) VALUES(?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement ps = this.getCn().prepareStatement(sql);
            ps.setString(1, reserva.getRESERVA_HORA_INICIO());
            ps.setString(2, reserva.getRESERVA_HORA_FIN());
            ps.setString(3, reserva.getRESERVA_DIA());
            ps.setString(4, reserva.getCLIENTE_CLIENTE_ID());
            ps.setString(5, reserva.getRESERVA_PAGO());
            ps.setString(6, reserva.getRESERVA_MONTO());
            ps.setString(7, reserva.getEMPLEADO_EMPLEADO_ID());
            ps.setString(8, reserva.getPRODUCTO_PRODUCTO_ID());
            ps.setString(9, reserva.getCANCHA_CANCHA_ID());
            ps.setString(10, reserva.getPRODUCTO_CANTIDAD());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw e;
        } finally {
            this.cerrar();
        }
    }

    public void modificar(ReservaM reserva) throws Exception {
        try {
            this.conectar();
            String sql = "UPDATE RESERVA SET RESERVA_HORA_INICIO=?,RESERVA_HORA_FIN=?, RESERVA_DIA=?,CLIENTE_CLIENTE_ID=?,RESERVA_PAGO=?,RESERVA_MONTO=?,EMPLEADO_EMPLEADO_ID=?,PRODUCTO_PRODUCTO_ID=?,CANCHA_CANCHA_ID=?,PRODUCTO_CANTIDAD=? WHERE RESERVA_ID=?";
            PreparedStatement ps = this.getCn().prepareStatement(sql);
            ps.setString(1, reserva.getRESERVA_HORA_INICIO());
            ps.setString(2, reserva.getRESERVA_HORA_FIN());
            ps.setString(3, reserva.getRESERVA_DIA());
            ps.setString(4, reserva.getCLIENTE_CLIENTE_ID());
            ps.setString(5, reserva.getRESERVA_PAGO());
            ps.setString(6, reserva.getRESERVA_MONTO());
            ps.setString(7, reserva.getEMPLEADO_EMPLEADO_ID());
            ps.setString(8, reserva.getPRODUCTO_PRODUCTO_ID());
            ps.setString(9, reserva.getPRODUCTO_CANTIDAD());
            ps.setString(10, reserva.getRESERVA_ID());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw e;
        } finally {
            this.cerrar();
        }
    }

    public List<ReservaM> listar() throws Exception {
        List<ReservaM> lista;
        ResultSet rs;
        try {
            this.conectar();
            String sql = "SELECT RESERVA_ID,RESERVA_HORA_INICIO,RESERVA_HORA_FIN,RESERVA_DIA,CONCAT(CLIENTE.NOMBRE_CLIE,' ',CLIENTE.APELLIDOS_CLIENTE) AS CLIENTE\n"
                    + ",RESERVA_PAGO,RESERVA_MONTO,CONCAT(EMPLEADO.EMPLEADO_NOMBRE,' ',EMPLEADO.EMPLEADO_APELLIDO) AS EMPLEADO,\n"
                    + "CONCAT(PRODUCTO.PRODUCTO_TIPO,' / ',PRODUCTO.PRODUCTO_DESCRIPCION) AS PRODUCTO,\n"
                    + "CONCAT(CANCHA.CANCHA_NOMBRE, ' / ',CANCHA.CANCHA_TIPO) AS CANCHA,PRODUCTO_CANTIDAD\n"
                    + "FROM RESERVA\n"
                    + "INNER JOIN CLIENTE ON RESERVA.CLIENTE_CLIENTE_ID = CLIENTE_ID\n"
                    + "INNER JOIN EMPLEADO ON RESERVA.EMPLEADO_EMPLEADO_ID = EMPLEADO_ID\n"
                    + "INNER JOIN PRODUCTO ON RESERVA.PRODUCTO_PRODUCTO_ID = PRODUCTO_ID\n"
                    + "INNER JOIN CANCHA ON RESERVA.CANCHA_CANCHA_ID = CANCHA_ID\n"
                    + "         ";
            PreparedStatement ps = this.getCn().prepareCall(sql);
            rs = ps.executeQuery();
            lista = new ArrayList<>();
            while (rs.next()) {
                ReservaM reserva = new ReservaM();
                reserva.setRESERVA_ID(rs.getString("RESERVA_ID"));
                reserva.setRESERVA_HORA_INICIO(rs.getString("RESERVA_HORA_INICIO"));
                reserva.setRESERVA_HORA_FIN(rs.getString("RESERVA_HORA_FIN"));
                reserva.setRESERVA_DIA(rs.getString("RESERVA_DIA"));
                reserva.setCLIENTE_CLIENTE_ID(rs.getString("CLIENTE"));
                reserva.setRESERVA_PAGO(rs.getString("RESERVA_PAGO"));
                reserva.setRESERVA_MONTO(rs.getString("RESERVA_MONTO"));
                reserva.setEMPLEADO_EMPLEADO_ID(rs.getString("EMPLEADO"));
                reserva.setPRODUCTO_PRODUCTO_ID(rs.getString("PRODUCTO"));
                reserva.setCANCHA_CANCHA_ID(rs.getString("CANCHA"));
                reserva.setPRODUCTO_CANTIDAD(rs.getString("PRODUCTO_CANTIDAD"));
                lista.add(reserva);
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            this.cerrar();
        }
        return lista;
    }

    public void eliminar(ReservaM reserva) throws Exception {
        this.conectar();
        try {
            String sql = "DELETE FROM RESERVA WHERE RESERVA_ID=?";
            PreparedStatement ps = this.getCn().prepareStatement(sql);
            ps.setString(1, reserva.getRESERVA_ID());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw e;
        } finally {
            this.cerrar();
        }
    }

}
