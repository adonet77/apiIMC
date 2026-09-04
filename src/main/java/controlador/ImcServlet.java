package controlador;

import com.google.gson.Gson;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.DatosImc;

@WebServlet(
        name = "ImcServlet",
        urlPatterns = {"/api/imc"}
)
public class ImcServlet extends HttpServlet {

    @Override
    protected void doOptions(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        // Permitimos que React pueda consumir la API.
        response.setHeader(
                "Access-Control-Allow-Origin",
                "http://localhost:5175"
        );

        // Métodos HTTP permitidos.
        response.setHeader(
                "Access-Control-Allow-Methods",
                "POST, OPTIONS"
        );

        // Encabezados HTTP permitidos.
        response.setHeader(
                "Access-Control-Allow-Headers",
                "Content-Type"
        );

        // La petición OPTIONS fue aceptada.
        response.setStatus(
                HttpServletResponse.SC_OK
        );
    }

    @Override
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        // Permitimos que React pueda consumir la API.
        response.setHeader(
                "Access-Control-Allow-Origin",
                "http://localhost:5173"
        );

        // Métodos HTTP permitidos.
        response.setHeader(
                "Access-Control-Allow-Methods",
                "POST, OPTIONS"
        );

        // Encabezados HTTP permitidos.
        response.setHeader(
                "Access-Control-Allow-Headers",
                "Content-Type"
        );

        // Creamos un StringBuilder para almacenar
        // el contenido del JSON recibido.
        StringBuilder json = new StringBuilder();

        String linea;

        // Leemos línea por línea el cuerpo de la petición.
        while ((linea = request.getReader().readLine()) != null) {

            // Agregamos cada línea al StringBuilder.
            json.append(linea);
        }

        // Creamos un objeto Gson.
        Gson gson = new Gson();

        // Convertimos el JSON recibido
        // en un objeto DatosImc.
        DatosImc datos = gson.fromJson(
                json.toString(),
                DatosImc.class
        );

        // Obtenemos los datos.
        String nombre = datos.getNombre();
        double peso = datos.getPeso();
        double altura = datos.getAltura();

        // Calculamos el IMC.
        double imc = peso / (altura * altura);

        // Variable para almacenar la clasificación.
        String clasificacion;

        // Determinamos la clasificación.
        if (imc < 18.5) {

            clasificacion = "Bajo peso";

        } else if (imc < 25.0) {

            clasificacion = "Peso normal";

        } else if (imc < 30.0) {

            clasificacion = "Sobrepeso";

        } else {

            clasificacion = "Obesidad";
        }

        // Indicamos que la respuesta será texto.
        response.setContentType(
                "text/plain;charset=UTF-8"
        );

        // Enviamos el nombre.
        response.getWriter().println(
                "Nombre: " + nombre
        );

        // Enviamos el peso.
        response.getWriter().println(
                "Peso: " + peso
        );

        // Enviamos la altura.
        response.getWriter().println(
                "Altura: " + altura
        );

        // Enviamos el IMC.
        response.getWriter().println(
                "IMC: " + imc
        );

        // Enviamos la clasificación.
        response.getWriter().println(
                "Clasificación: " + clasificacion
        );
    }
}