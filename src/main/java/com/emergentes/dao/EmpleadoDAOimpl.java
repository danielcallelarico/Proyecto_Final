package com.emergentes.dao;


import com.emergentes.modelo.Empleado;
import com.emergentes.utiles.ConexionDB;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class EmpleadoDAOimpl extends ConexionDB implements EmpleadoDAO {

    @Override
    public void insert(Empleado empleado) throws Exception {
        try {
            this.conectar();
            PreparedStatement ps = this.conn.prepareStatement("INSERT INTO empleados (nombre,celular,cargo,sueldo) values (?,?,?,?)");
            ps.setString(1, empleado.getNombre());
            ps.setString(2, empleado.getCelular());
            ps.setString(3, empleado.getCargo());
            ps.setInt(4, empleado.getSueldo());
            ps.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            this.desconectar();
        }
    }

    @Override
    public void update(Empleado empleado) throws Exception {
        try {
            this.conectar();
            PreparedStatement ps = this.conn.prepareStatement("UPDATE empleados set nombre = ?, celular = ?, cargo = ? ,sueldo = ? where id = ?");
            ps.setString(1, empleado.getNombre());
            ps.setString(2, empleado.getCelular());
            ps.setString(3, empleado.getCargo());
            ps.setInt(4, empleado.getSueldo());
            ps.setInt(5, empleado.getId());
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
            PreparedStatement ps = this.conn.prepareStatement("DELETE FROM empleados WHERE id= ?");
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            this.desconectar();
        }
    }

    @Override
    public Empleado getById(int id) throws Exception {
        Empleado cli = new Empleado();
        try {

            this.conectar();

            PreparedStatement ps = this.conn.prepareStatement("SELECT * FROM empleados WHERE id = ? limit 1");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                cli.setId(rs.getInt("id"));
                cli.setNombre(rs.getString("nombre"));
                cli.setCelular(rs.getString("celular"));
                cli.setCargo(rs.getString("cargo"));
                cli.setSueldo(rs.getInt("sueldo"));
            }
        } catch (Exception e) {
            throw e;
        } finally {
            this.desconectar();
        }
        return cli;    
    }

    @Override
    public List<Empleado> getAll() throws Exception {
        List<Empleado> lista = null;
        try{
            this.conectar();
            PreparedStatement ps = this.conn.prepareStatement("SELECT * FROM empleados");
            ResultSet rs = ps.executeQuery();
            
            lista = new ArrayList<Empleado>();
            
            while(rs.next()){
                Empleado cli = new Empleado();
                System.out.println("Fila ...");
                cli.setId(rs.getInt("id"));
                cli.setNombre(rs.getString("nombre"));
                cli.setCelular(rs.getString("celular"));
                cli.setCargo(rs.getString("cargo"));
                cli.setSueldo(rs.getInt("sueldo"));
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


