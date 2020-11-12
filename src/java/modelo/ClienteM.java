package modelo;

public class ClienteM {

    private String CLIENTE_ID;
    private String NOMBRE_CLIE;
    private String APELLIDOS_CLIENTE;
    private String CELULAR_CLIENTE;
    private String CLIENTE_USUARIO;
    private String CLIENTE_PASS;

    //GETTER AND SETTER
    public String getCLIENTE_ID() {
        return CLIENTE_ID;
    }

    public void setCLIENTE_ID(String CLIENTE_ID) {
        this.CLIENTE_ID = CLIENTE_ID;
    }

    public String getNOMBRE_CLIE() {
        return NOMBRE_CLIE;
    }

    public void setNOMBRE_CLIE(String NOMBRE_CLIE) {
        this.NOMBRE_CLIE = NOMBRE_CLIE;
    }

    public String getAPELLIDOS_CLIENTE() {
        return APELLIDOS_CLIENTE;
    }

    public void setAPELLIDOS_CLIENTE(String APELLIDOS_CLIENTE) {
        this.APELLIDOS_CLIENTE = APELLIDOS_CLIENTE;
    }

    public String getCELULAR_CLIENTE() {
        return CELULAR_CLIENTE;
    }

    public void setCELULAR_CLIENTE(String CELULAR_CLIENTE) {
        this.CELULAR_CLIENTE = CELULAR_CLIENTE;
    }

    public String getCLIENTE_USUARIO() {
        return CLIENTE_USUARIO;
    }

    public void setCLIENTE_USUARIO(String CLIENTE_USUARIO) {
        this.CLIENTE_USUARIO = CLIENTE_USUARIO;
    }

    public String getCLIENTE_PASS() {
        return CLIENTE_PASS;
    }

    public void setCLIENTE_PASS(String CLIENTE_PASS) {
        this.CLIENTE_PASS = CLIENTE_PASS;
    }

}
