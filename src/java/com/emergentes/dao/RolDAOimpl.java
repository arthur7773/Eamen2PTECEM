package com.emergentes.dao;

import com.emergentes.modelo.Rol;
import com.emergentes.utiles.ConexionBD;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class RolDAOimpl extends ConexionBD implements RolDAO {

    @Override
    public void insert(Rol rol) throws Exception {
        try {
            PreparedStatement ps = this.conn.prepareStatement("INSERT INTO roles (descripcion) VALUES (?)");
            ps.setString(1, rol.getDescripcion());
            ps.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            this.desconectar();
        }
    }

    @Override
    public void update(Rol rol) throws Exception {
        try {
            this.conectar();
            PreparedStatement ps = this.conn.prepareStatement("UPDATE roles SET descripcion = ? WHERE id = ?");
            ps.setString(1, rol.getDescripcion());
            ps.setInt(2, rol.getId());
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
            PreparedStatement ps = this.conn.prepareStatement("DELETE FROM roles WHERE id = ?");
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            this.desconectar();
        }
    }

    @Override
    public Rol getById(int id) throws Exception {
        Rol ges = new Rol();
        try {
            this.conectar();
            PreparedStatement ps = this.conn.prepareStatement("SELECT * FROM roles WHERE id = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                ges.setId(rs.getInt("id"));
                ges.setDescripcion(rs.getString("descripcion"));
            }
        } catch (Exception e) {
            throw e;
        } finally {
            this.desconectar();
        }
        return ges;
    }

    @Override
    public List<Rol> getAll() throws Exception {
        List<Rol> lista = null;
        try {
            this.conectar();
            PreparedStatement ps = this.conn.prepareStatement("SELECT * FROM roles");
            ResultSet rs = ps.executeQuery();

            lista = new ArrayList<Rol>();
            while (rs.next()) {
                Rol r = new Rol();
                r.setId(rs.getInt("id"));
                r.setDescripcion(rs.getString("descripcion"));
                lista.add(r);
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