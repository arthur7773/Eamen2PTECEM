package com.emergentes.modelo;

public class Permiso {
    
    private int id;
    String id_usuario;
    String id_rol;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(String id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getId_rol() {
        return id_rol;
    }

    public void setId_rol(String id_rol) {
        this.id_rol = id_rol;
    }
    
    @Override
    public String toString() {
        return "Permiso{" + "id=" + id + ", id_usuario=" + id_usuario + ", id_rol=" + id_rol + '}';
    }
}
