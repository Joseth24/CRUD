package modelo;

public class ReservaM {

    private String RESERVA_ID;
    private String RESERVA_HORA_INICIO;
    private String RESERVA_HORA_FIN;
    private String RESERVA_DIA;
    private String CLIENTE_CLIENTE_ID;
    private String RESERVA_PAGO;
    private String RESERVA_MONTO;
    private String EMPLEADO_EMPLEADO_ID;
    private String PRODUCTO_PRODUCTO_ID;
    private String CANCHA_CANCHA_ID;
    private String PRODUCTO_CANTIDAD;

//GETTER AND SETTER
    public String getRESERVA_ID() {
        return RESERVA_ID;
    }

    public void setRESERVA_ID(String RESERVA_ID) {
        this.RESERVA_ID = RESERVA_ID;
    }

    public String getRESERVA_HORA_INICIO() {
        return RESERVA_HORA_INICIO;
    }

    public void setRESERVA_HORA_INICIO(String RESERVA_HORA_INICIO) {
        this.RESERVA_HORA_INICIO = RESERVA_HORA_INICIO;
    }

    public String getRESERVA_HORA_FIN() {
        return RESERVA_HORA_FIN;
    }

    public void setRESERVA_HORA_FIN(String RESERVA_HORA_FIN) {
        this.RESERVA_HORA_FIN = RESERVA_HORA_FIN;
    }

    public String getRESERVA_DIA() {
        return RESERVA_DIA;
    }

    public void setRESERVA_DIA(String RESERVA_DIA) {
        this.RESERVA_DIA = RESERVA_DIA;
    }

    public String getCLIENTE_CLIENTE_ID() {
        return CLIENTE_CLIENTE_ID;
    }

    public void setCLIENTE_CLIENTE_ID(String CLIENTE_CLIENTE_ID) {
        this.CLIENTE_CLIENTE_ID = CLIENTE_CLIENTE_ID;
    }

    public String getRESERVA_PAGO() {
        return RESERVA_PAGO;
    }

    public void setRESERVA_PAGO(String RESERVA_PAGO) {
        this.RESERVA_PAGO = RESERVA_PAGO;
    }

    public String getRESERVA_MONTO() {
        return RESERVA_MONTO;
    }

    public void setRESERVA_MONTO(String RESERVA_MONTO) {
        this.RESERVA_MONTO = RESERVA_MONTO;
    }

    public String getEMPLEADO_EMPLEADO_ID() {
        return EMPLEADO_EMPLEADO_ID;
    }

    public void setEMPLEADO_EMPLEADO_ID(String EMPLEADO_EMPLEADO_ID) {
        this.EMPLEADO_EMPLEADO_ID = EMPLEADO_EMPLEADO_ID;
    }

    public String getPRODUCTO_PRODUCTO_ID() {
        return PRODUCTO_PRODUCTO_ID;
    }

    public void setPRODUCTO_PRODUCTO_ID(String PRODUCTO_PRODUCTO_ID) {
        this.PRODUCTO_PRODUCTO_ID = PRODUCTO_PRODUCTO_ID;
    }

    public String getCANCHA_CANCHA_ID() {
        return CANCHA_CANCHA_ID;
    }

    public void setCANCHA_CANCHA_ID(String CANCHA_CANCHA_ID) {
        this.CANCHA_CANCHA_ID = CANCHA_CANCHA_ID;
    }

    public String getPRODUCTO_CANTIDAD() {
        return PRODUCTO_CANTIDAD;
    }

    public void setPRODUCTO_CANTIDAD(String PRODUCTO_CANTIDAD) {
        this.PRODUCTO_CANTIDAD = PRODUCTO_CANTIDAD;
    }

}
