
package com.emergentes.dao;

import com.emergentes.modelo.Producto;
import com.emergentes.utiles.ConexionDB;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ProductoDAOimpl extends ConexionDB implements ProductoDAO{

    @Override
    public void insert(Producto producto) throws Exception {
         try {
            this.conectar();
            PreparedStatement ps = this.conn.prepareStatement("INSERT INTO productos (marca,cantidad,precio,descripcion) values (?,?,?,?)");
            ps.setString(1, producto.getMarca());
            ps.setInt(2, producto.getCantidad());
            ps.setFloat(3, Float.valueOf(producto.getCantidad()));
            ps.setString(4, producto.getDescripcion()); 
            ps.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            this.desconectar();
        }
    }

    @Override
    public void update(Producto producto) throws Exception {
          try {
            this.conectar();
            PreparedStatement ps = this.conn.prepareStatement("UPDATE productos set marca = ?, cantidad = ?, precio = ? ,descripcion = ? where id = ?");
            ps.setString(1, producto.getMarca());
            ps.setInt(2, producto.getCantidad());
            ps.setFloat(3, Float.valueOf(producto.getCantidad()));
            ps.setString(4, producto.getDescripcion());
            ps.setInt(5,producto.getId());
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
            PreparedStatement ps = this.conn.prepareStatement("DELETE FROM productos WHERE id= ?");
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            this.desconectar();
        }
    }

    @Override
    public Producto getById(int id) throws Exception {
        Producto cli = new Producto();
        try {

            this.conectar();

            PreparedStatement ps = this.conn.prepareStatement("SELECT * FROM productos WHERE id = ? limit 1");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                cli.setId(rs.getInt("id"));
                cli.setMarca(rs.getString("marca"));
                cli.setCantidad(rs.getInt("cantidad"));
                cli.setPrecio(rs.getFloat("precio"));
                cli.setDescripcion(rs.getString("descripcion"));
            }
        } catch (Exception e) {
            throw e;
        } finally {
            this.desconectar();
        }
        return cli;    
    }

    @Override
    public List<Producto> getAll() throws Exception {
         List<Producto> lista = null;
        try{
            this.conectar();
            PreparedStatement ps = this.conn.prepareStatement("SELECT * FROM productos");
            ResultSet rs = ps.executeQuery();
            
            lista = new ArrayList<Producto>();
            
            while(rs.next()){
                Producto cli = new Producto();
                System.out.println("Fila ...");
                cli.setId(rs.getInt("id"));
                cli.setMarca(rs.getString("marca"));
                cli.setCantidad(rs.getInt("cantidad"));
                cli.setPrecio(rs.getFloat("precio"));
                cli.setDescripcion(rs.getString("descripcion"));
                lista.add(cli);
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
    

