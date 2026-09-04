package controlador;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(
        name = "Error404Servlet",
        urlPatterns = {"/error/404"}
)
public class Error404Servlet extends HttpServlet {

    @Override
    protected void service(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        // Indicamos que la respuesta será JSON.
        response.setContentType(
                "application/json;charset=UTF-8"
        );

        // Establecemos el código HTTP 404.
        response.setStatus(
                HttpServletResponse.SC_NOT_FOUND
        );

        // Obtenemos la URL solicitada.
        String uri = request.getRequestURI();

        // Construimos el mensaje de error.
        String json =
                "{"
                + "\"error\":\"Endpoint no encontrado\","
                + "\"codigo\":404,"
                + "\"ruta\":\"" + uri + "\""
                + "}";

        // Enviamos el JSON como respuesta.
        response.getWriter().println(json);
    }
}