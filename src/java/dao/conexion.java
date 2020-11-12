package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class conexion {

    private Connection cn;

    public void conectar() throws SQLException, ClassNotFoundException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/RESERVA", "jcastilla", "jcastilla");
//            cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/RESERVA", "root", "jcastilla");
            System.out.println("Conectado");
        } catch (ClassNotFoundException e) {
            System.out.println("Error: " + e);
        }
    }

    public void cerrar() throws Exception {
        try {
            if (cn != null) {
                if (cn.isClosed() == false) {
                    cn.close();
                }
            }
        } catch (SQLException e) {
            throw e;
        }
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        try {
            conexion dao = new conexion();
            dao.conectar();
        } catch (ClassNotFoundException | SQLException e) {
            throw e;
        }
    }

    public Connection getCn() {
        return cn;
    }

    public void setCn(Connection cn) {
        this.cn = cn;
    }

}
