
package com.emergentes.modelo;

public class Venta {
    private int id;
    private int producto_id;
    private int cliente_id;
    private int cantidad;
    private String fecha;
    private String producto;
    private String cliente;

    public Venta() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProducto_id() {
        return producto_id;
    }

    public void setProducto_id(int producto_id) {
        this.producto_id = producto_id;
    }

    public int getCliente_id() {
        return cliente_id;
    }

    public void setCliente_id(int cliente_id) {
        this.cliente_id = cliente_id;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }
    @Override
    public String toString() {
        return "Venta{" + "id=" + id + ", cliente_id=" + cliente_id + ", producto_id=" + producto_id + ", cantidad="+ cantidad + ", fecha=" + fecha + ", cliente=" 
                + cliente + ", producto=" + producto + '}';
    }

    public void setProducto_id(String producto_id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void setCliente_id(String cliente_id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
