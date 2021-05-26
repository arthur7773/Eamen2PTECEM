package com.emergentes.dao;

import com.emergentes.modelo.Permiso;
import com.emergentes.utiles.ConexionBD;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class PermisoDAOimpl extends ConexionBD implements PermisoDAO{

    @Override
    public void insert(Permiso permiso) throws Exception {
        try {
            PreparedStatement ps = this.conn.prepareStatement("INSERT INTO permisos (id_usuario, id_rol) VALUES (?, ?)");
            ps.setString(1, permiso.getId_usuario());
            ps.setString(2, permiso.getId_rol());
            ps.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            this.desconectar();
        }
    }

    @Override
    public void update(Permiso permiso) throws Exception {
        try {
            this.conectar();
            PreparedStatement ps = this.conn.prepareStatement("UPDATE permisos SET id_usuario = ?, id_rol = ? WHERE id = ?");
            ps.setString(1, permiso.getId_usuario());
            ps.setString(2, permiso.getId_rol());
            ps.setInt(3, permiso.getId());
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
            PreparedStatement ps = this.conn.prepareStatement("DELETE FROM permisos WHERE id = ?");
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            this.desconectar();
        }
    }

    @Override
    public Permiso getById(int id) throws Exception {
        Permiso per = new Permiso();
        try {
            this.conectar();
            PreparedStatement ps = this.conn.prepareStatement("SELECT * FROM permisos WHERE id = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                per.setId(rs.getInt("id"));
                per.setId_usuario(rs.getString("id_usuario"));
                per.setId_rol(rs.getString("id_rol"));
            }
        } catch (Exception e) {
            throw e;
        } finally {
            this.desconectar();
        }
        return per;
    }

    @Override
    public List<Permiso> getAll() throws Exception {
        List<Permiso> lista = null;
        try {
            this.conectar();
            PreparedStatement ps = this.conn.prepareStatement("SELECT * FROM permisos");
            ResultSet rs = ps.executeQuery();

            lista = new ArrayList<Permiso>();
            while (rs.next()) {
                Permiso per = new Permiso();
                per.setId(rs.getInt("id"));
                per.setId_usuario(rs.getString("id_usuario"));
                per.setId_rol(rs.getString("id_rol"));
                lista.add(per);
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            throw e;
        } finally {
            this.desconectar();
        }
        return lista;
    }
    
}
