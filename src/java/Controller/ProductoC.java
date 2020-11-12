package Controller;

import dao.Producto;
import dao.Proveedor;
import modelo.ProductoM;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

@Named(value = "productoC")
@SessionScoped
public class ProductoC implements Serializable {

    private ProductoM producto = new ProductoM();
    private List<ProductoM> lstProducto;
    private ProductoM selectProducto;

    @PostConstruct
    public void init() {
        try {
            listar();
        } catch (Exception e) {
        }
    }

    public void registrar() throws Exception {
        Producto dao;
        Proveedor proveedor;
        try {
            dao = new Producto();
            proveedor = new Proveedor();
            producto.setPROVEEDOR_PROVEEDOR_ID(proveedor.obtenerCodigoProveedor(producto.getPROVEEDOR_PROVEEDOR_ID()));
            dao.registrar(producto);
            listar();
            limpiar();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "AGREGADO", null));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "ERROR", null));
            throw e;
        }
    }

    public void modificar() throws Exception {
        Producto dao;
        try {
            dao = new Producto();
            dao.modificar(selectProducto);
            listar();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "MODIFICADO", null));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "ERROR", null));
            throw e;
        }
    }

    public void listar() throws Exception {
        Producto dao;
        try {
            dao = new Producto();
            lstProducto = dao.listar();
        } catch (Exception e) {
            throw e;
        }
    }

    public void eliminar() throws Exception {
        Producto dao;
        try {
            dao = new Producto();
            dao.eliminar(selectProducto);
            listar();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "ELIMINADO", null));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "ERROR", null));
            throw e;
        }
    }

    //AUTOCOMPLETAR PROVEEDOR
    public List<String> completeProveedor(String query) throws Exception {
        Proveedor ubigeo = new Proveedor();
        return ubigeo.autocompleteProveedor(query);
    }

    public void limpiar() {
        producto = new ProductoM();
    }

    //GETTER AND SETTER
    public ProductoM getProducto() {
        return producto;
    }

    public void setProducto(ProductoM producto) {
        this.producto = producto;
    }

    public List<ProductoM> getLstProducto() {
        return lstProducto;
    }

    public void setLstProducto(List<ProductoM> lstProducto) {
        this.lstProducto = lstProducto;
    }

    public ProductoM getSelectProducto() {
        return selectProducto;
    }

    public void setSelectProducto(ProductoM selectProducto) {
        this.selectProducto = selectProducto;
    }

}
