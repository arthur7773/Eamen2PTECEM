package com.emergentes.controlador;

import com.emergentes.dao.UsuarioDAO;
import com.emergentes.dao.UsuarioDAOimpl;
import com.emergentes.modelo.Usuario;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "UsuarioController", urlPatterns = {"/UsuarioController"})
public class UsuarioController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            UsuarioDAO dao = new UsuarioDAOimpl();
            int id;
            Usuario usu = new Usuario();

            String action = (request.getParameter("action") != null) ? request.getParameter("action") : "view";
            switch (action) {
                case "add":
                    request.setAttribute("usuario", usu);
                    request.getRequestDispatcher("frmusuario.jsp").forward(request, response);
                    break;
                case "edit":
                    id = Integer.parseInt(request.getParameter("id"));
                    usu = dao.getById(id);
                    request.setAttribute("usuario", usu);
                    request.getRequestDispatcher("frmusuario.jsp").forward(request, response);
                    break;
                case "delete":
                    id = Integer.parseInt(request.getParameter("id"));
                    dao.delete(id);
                    response.sendRedirect(request.getContextPath() + "/UsuarioController");
                    break;
                case "view":
                    List<Usuario> lista = dao.getAll();
                    request.setAttribute("usuarios", lista);
                    request.getRequestDispatcher("usuarios.jsp").forward(request, response);
                    break;
                default:
                    break;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String usuario = request.getParameter("usuario");
        String correo = request.getParameter("correo");
        String clave = request.getParameter("clave");

        Usuario usu = new Usuario();

        usu.setId(id);
        usu.setUsuario(usuario);
        usu.setCorreo(correo);
        usu.setClave(clave);

        if (id == 0) {
            try {
                UsuarioDAO dao = new UsuarioDAOimpl();
                dao.insert(usu);
                response.sendRedirect(request.getContextPath() + "/UsuarioController");
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        } else {
            try {
                UsuarioDAO dao = new UsuarioDAOimpl();
                dao.update(usu);
                response.sendRedirect(request.getContextPath() + "/UsuarioController");
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
    }
}
