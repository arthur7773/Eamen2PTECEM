package com.emergentes.controlador;

import com.emergentes.dao.PermisoDAO;
import com.emergentes.dao.PermisoDAOimpl;
import com.emergentes.modelo.Permiso;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "PermisoController", urlPatterns = {"/PermisoController"})
public class PermisoController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            PermisoDAO dao = new PermisoDAOimpl();
            int id;
            Permiso per = new Permiso();

            String action = (request.getParameter("action") != null) ? request.getParameter("action") : "view";
            switch (action) {
                case "add":
                    request.setAttribute("permiso", per);
                    request.getRequestDispatcher("frmpermiso.jsp").forward(request, response);
                    break;
                case "edit":
                    id = Integer.parseInt(request.getParameter("id"));
                    per = dao.getById(id);
                    request.setAttribute("permiso", per);
                    request.getRequestDispatcher("frmpermiso.jsp").forward(request, response);
                    break;
                case "delete":
                    id = Integer.parseInt(request.getParameter("id"));
                    dao.delete(id);
                    response.sendRedirect(request.getContextPath() + "/PermisoController");
                    break;
                case "view":
                    List<Permiso> lista = dao.getAll();
                    request.setAttribute("permisos", lista);
                    request.getRequestDispatcher("permisos.jsp").forward(request, response);
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
        String id_usuario = request.getParameter("id_usuario");
        String id_rol = request.getParameter("id_rol");

        Permiso per = new Permiso();

        per.setId(id);
        per.setId_usuario(id_usuario);
        per.setId_rol(id_rol);

        if (id == 0) {
            try {
                PermisoDAO dao = new PermisoDAOimpl();
                dao.insert(per);
                response.sendRedirect(request.getContextPath() + "/PermisoController");
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        } else {
            try {
                PermisoDAO dao = new PermisoDAOimpl();
                dao.update(per);
                response.sendRedirect(request.getContextPath() + "/PermisoController");
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
    }
}
