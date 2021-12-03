
package com.emergentes.dao;

import com.emergentes.modelo.Proveedor;
import com.emergentes.utiles.ConexionDB;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ProveedorDAOimpl extends ConexionDB implements ProveedorDAO{

    @Override
    public void insert(Proveedor proveedor) throws Exception {
        try {
            this.conectar();
            PreparedStatement ps = this.conn.prepareStatement("INSERT INTO proveedores (empresa,nombre_prov,celular,direccion) values (?,?,?,?)");
            ps.setString(1, proveedor.getEmpresa());
            ps.setString(2, proveedor.getNombre_prov());
            ps.setString(3, proveedor.getCelular());
            ps.setString(3, proveedor.getDireccion());
            ps.executeUpdate(); 
            ps.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            this.desconectar();
        }
    }


    @Override
    public void update(Proveedor proveedor) throws Exception {
        try{
            this.conectar();
            PreparedStatement ps = this.conn.prepareStatement("UPDATE proveedores set empresa = ?, nombre_prov = ?, celular = ?, direccion = ? where id = ?");
            ps.setString(1, proveedor.getEmpresa());
            ps.setString(2, proveedor.getNombre_prov());            
            ps.setString(3, proveedor.getCelular());            
            ps.setString(3, proveedor.getDireccion());            
            ps.setInt(4,proveedor.getId());
            ps.executeUpdate();
        }catch(Exception e){
            throw e;
        }finally{
            this.desconectar();
        }
    }

    @Override
    public void delete(int id) throws Exception {
        try {
            this.conectar();
            PreparedStatement ps = this.conn.prepareStatement("DELETE FROM proveedores WHERE id= ?");
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            this.desconectar();
        }
    }

    @Override
    public Proveedor getById(int id) throws Exception {
        Proveedor cli = new Proveedor();
        try {
            this.conectar();
            PreparedStatement ps = this.conn.prepareStatement("SELECT * FROM proveedores WHERE id = ? limit 1");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                cli.setId(rs.getInt("id"));
                cli.setEmpresa(rs.getString("empresa"));
                cli.setNombre_prov(rs.getString("nombre_prov"));
                cli.setCelular(rs.getString("celular"));
                cli.setDireccion(rs.getString("direccion"));
            }
        } catch (Exception e) {
            throw e;
        } finally {
            this.desconectar();
        }
        return cli;
    }

    @Override
    public List<Proveedor> getAll() throws Exception {
        List<Proveedor> lista = null;
        try{
            this.conectar();
            PreparedStatement ps = this.conn.prepareStatement("SELECT * FROM proveedores");
            ResultSet rs = ps.executeQuery();
            
            lista = new ArrayList<Proveedor>();
            while(rs.next()){
                Proveedor cli = new Proveedor();
                System.out.println("Fila ...");
                cli.setId(rs.getInt("id"));
                cli.setEmpresa(rs.getString("empresa"));
                cli.setNombre_prov(rs.getString("nombre_prov"));
                cli.setCelular(rs.getString("celular"));
                cli.setDireccion(rs.getString("direccion"));
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
    

