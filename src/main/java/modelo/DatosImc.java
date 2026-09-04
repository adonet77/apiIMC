
package modelo;

/**
 *
 * @author ADONET
 */
public class DatosImc {

    // Nombre del usuario.
    private String nombre;

    // Peso del usuario en kilogramos.
    private double peso;

    // Altura del usuario en metros.
    private double altura;
    
   // Constructor vacío.
    public DatosImc() {
    }

    // Obtener el nombre.
    public String getNombre() {
        return nombre;
    }

    // Asignar el nombre.
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    // Obtener el peso.
    public double getPeso() {
        return peso;
    }

    // Asignar el peso.
    public void setPeso(double peso) {
        this.peso = peso;
    }

    // Obtener la altura.
    public double getAltura() {
        return altura;
    }

    // Asignar la altura.
    public void setAltura(double altura) {
        this.altura = altura;
    }
}