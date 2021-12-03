
package com.emergentes.dao;

import com.emergentes.modelo.Venta;
import com.emergentes.utiles.ConexionDB;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;



public class VentaDAOimpl extends ConexionDB implements VentaDAO {

    @Override
    public void insert(Venta venta) throws Exception {
        try {
            this.conectar();

            PreparedStatement ps = this.conn.prepareStatement("INSERT into salidas (producto_id, cliente_id,cantidad, fecha) values (?, ?, ?, ?)");
            ps.setInt(1, venta.getProducto_id());
            ps.setInt(2, venta.getCliente_id());
            ps.setInt(3, venta.getCantidad());
            ps.setString(4, venta.getFecha());
            ps.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            this.desconectar();
        }        
    }

    @Override
    public void update(Venta venta) throws Exception {
         try {
            this.conectar();
            String sql = "UPDATE salidas SET producto_id = ?,cliente_id = ?,cantidad = ? ,fecha = ? where id = ?";
            PreparedStatement ps = this.conn.prepareStatement(sql);
             ps.setInt(1, venta.getProducto_id());
            ps.setInt(2, venta.getCliente_id());
            ps.setInt(3, venta.getCantidad());
            ps.setString(4, venta.getFecha());
            ps.setInt(5, venta.getId());
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
            String sql = "DELETE FROM salidas WHERE id = ?";
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
    public Venta getById(int id) throws Exception {
         Venta ven = new Venta();
        try {
            this.conectar();
            String sql = "SELECT * FROM salidas WHERE id = ?";
            PreparedStatement ps = this.conn.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                ven.setId(rs.getInt("id"));
                ven.setProducto_id(rs.getInt("producto_id"));
                ven.setCliente_id(rs.getInt("cliente_id"));
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
    public List<Venta> getAll() throws Exception {
         List<Venta> lista = null;
        try{
            this.conectar();
            String sql = "SELECT v.*,p.descripcion as producto, c.nombre as cliente FROM salidas v ";
                    sql += "LEFT JOIN productos p on v.producto_id = p.id ";
                    sql += "LEFT JOIN clientes c on v.cliente_id = c.id ";
            PreparedStatement ps = this.conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            
            lista = new ArrayList<Venta>();
            while(rs.next()){
                Venta venta = new Venta();
                venta.setId(rs.getInt("id"));
                venta.setCliente_id(rs.getInt("cliente_id"));
                venta.setProducto_id(rs.getInt("producto_id")); 
                venta.setCantidad(rs.getInt("cantidad"));
                venta.setFecha(rs.getString("fecha"));
                venta.setCliente(rs.getString("cliente"));  
                venta.setProducto(rs.getString("producto"));                 
                System.out.println("Fila mandada : "+ venta.toString());                
                lista.add(venta);
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
    

