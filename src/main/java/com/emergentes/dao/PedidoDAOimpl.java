
package com.emergentes.dao;

import com.emergentes.modelo.Pedido;
import com.emergentes.utiles.ConexionDB;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class PedidoDAOimpl extends ConexionDB implements PedidoDAO{

    @Override
    public void insert(Pedido pedido) throws Exception {
        try {
            this.conectar();

            PreparedStatement ps = this.conn.prepareStatement("INSERT into entradas (producto_id, proveedor_id,cantidad, fecha) values (?, ?, ?, ?)");
            ps.setInt(1, pedido.getProducto_id());
            ps.setInt(2, pedido.getProveedor_id());
            ps.setInt(3, pedido.getCantidad());
            ps.setString(4, pedido.getFecha());
            ps.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            this.desconectar();
        }        
    }

    @Override
    public void update(Pedido pedido) throws Exception {
        try {
            this.conectar();
            String sql = "UPDATE entradas SET producto_id = ?,proveedor_id = ?,cantidad = ? ,fecha = ? where id = ?";
            PreparedStatement ps = this.conn.prepareStatement(sql);
             ps.setInt(1, pedido.getProducto_id());
            ps.setInt(2, pedido.getProveedor_id());
            ps.setInt(3, pedido.getCantidad());
            ps.setString(4, pedido.getFecha());
            ps.setInt(5, pedido.getId());
            ps.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            this.desconectar();
        }
    }

    @Override
    public void delete(int id) throws Exception {
        try {
            this.conectar();
            String sql = "DELETE FROM entradas WHERE id = ?";
            PreparedStatement ps = this.conn.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            this.desconectar();
        }
    }

    @Override
    public Pedido getById(int id) throws Exception {
        Pedido ven = new Pedido();
        try {
            this.conectar();
            String sql = "SELECT * FROM entradas WHERE id = ?";
            PreparedStatement ps = this.conn.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                ven.setId(rs.getInt("id"));
                ven.setProducto_id(rs.getInt("producto_id"));
                ven.setProveedor_id(rs.getInt("proveedor_id"));
                ven.setCantidad(rs.getInt("cantidad"));
                ven.setFecha(rs.getString("fecha"));
            }
        } catch (Exception e) {
            throw e;
        } finally {
            this.desconectar();
        }
        return ven;
    }

    @Override
    public List<Pedido> getAll() throws Exception {
        List<Pedido> lista = null;
        try{
            this.conectar();
            String sql = "SELECT v.*,p.descripcion as producto, c.empresa as proveedor FROM entradas v ";
                    sql += "LEFT JOIN productos p on v.producto_id = p.id ";
                    sql += "LEFT JOIN proveedores c on v.proveedor_id = c.id; ";
            PreparedStatement ps = this.conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            
            lista = new ArrayList<Pedido>();
            while(rs.next()){
                Pedido ped = new Pedido();
                ped.setId(rs.getInt("id"));
                ped.setProducto_id(rs.getInt("producto_id"));
                ped.setProveedor_id(rs.getInt("proveedor_id"));
                ped.setCantidad(rs.getInt("cantidad"));
                ped.setFecha(rs.getString("fecha"));
                ped.setProducto(rs.getString("producto"));
                ped.setProveedor(rs.getString("proveedor"));                
                System.out.println("Fila: "+ ped.toString());                
                lista.add(ped);
            }
            rs.close();
            ps.close();
        }catch(Exception e){
            throw e;
        }finally{
            this.desconectar();
        }  
        return lista;        
    }
 
}
    

