package com.emergentes.dao; 

import com.emergentes.utiles.Hash;
import com.emergentes.modelo.Usuario;
import com.emergentes.utiles.ConexionBD;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAOimpl extends ConexionBD implements UsuarioDAO {

    Hash h = new Hash();
    
    @Override
    public void insert(Usuario usuario) throws Exception {
        try {
            PreparedStatement ps = this.conn.prepareStatement("INSERT INTO usuarios (usuario, correo, clave) VALUES (?, ?, ?)");
            ps.setString(1, usuario.getUsuario());
            ps.setString(2, usuario.getCorreo());
            String hash = Hash.getHash(usuario.getClave(), "MD5");
            ps.setString(3, hash);
            ps.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            this.desconectar();
        }
    }

    @Override
    public void update(Usuario usuario) throws Exception {
        try {
            this.conectar();
            PreparedStatement ps = this.conn.prepareStatement("UPDATE usuarios SET usuario = ?, correo = ?, clave = ? WHERE id = ?");
            ps.setString(1, usuario.getUsuario());
            ps.setString(2, usuario.getCorreo());
            String hash = Hash.getHash(usuario.getClave(), "MD5");
            ps.setString(3, hash);
            ps.setInt(4, usuario.getId());
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
            PreparedStatement ps = this.conn.prepareStatement("DELETE FROM usuarios WHERE id = ?");
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            this.desconectar();
        }
    }

    @Override
    public Usuario getById(int id) throws Exception {
        Usuario avi = new Usuario();
        try {
            this.conectar();
            PreparedStatement ps = this.conn.prepareStatement("SELECT * FROM usuarios WHERE id = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                avi.setId(rs.getInt("id"));
                avi.setUsuario(rs.getString("usuario"));
                avi.setCorreo(rs.getString("correo"));
                avi.setClave(rs.getString("clave"));
            }
        } catch (Exception e) {
            throw e;
        } finally {
            this.desconectar();
        }
        return avi;
    }

    @Override
    public List<Usuario> getAll() throws Exception {
        List<Usuario> lista = null;
        try {
            this.conectar();
            PreparedStatement ps = this.conn.prepareStatement("SELECT * FROM usuarios");
            ResultSet rs = ps.executeQuery();

            lista = new ArrayList<Usuario>();
            while (rs.next()) {
                Usuario usu = new Usuario();
                usu.setId(rs.getInt("id"));
                usu.setUsuario(rs.getString("usuario"));
                usu.setCorreo(rs.getString("correo"));
                usu.setClave(rs.getString("clave"));
                lista.add(usu);
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
