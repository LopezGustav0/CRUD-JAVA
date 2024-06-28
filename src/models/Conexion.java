package models;
import java.sql.Connection;
import java.sql.DriverManager;
// Declaración de la clase Conexion en el paquete models
public class Conexion {
    // Método estático para obtener una conexión a la base de datos
    public static Connection getConexion() {
        Connection conexion; // Declaración de la variable de conexión
        try {
            // Carga la clase del controlador de la base de datos MySQL
            Class.forName("com.mysql.jdbc.Driver"); 
            // Establece la cadena de conexión con la base de datos
            String cadena = "jdbc:mysql://localhost:3306/bdpiad303";
            String user = "root"; // Nombre de usuario de la base de datos
            String clave = ""; // Contraseña de la base de datos
            // Establece la conexión a la base de datos utilizando DriverManager
            conexion = DriverManager.getConnection(cadena, user, clave);
        } catch (Exception e) { // Captura de excepciones
            conexion = null; // Establece la conexión como nula en caso de error
            System.out.println("Error"); // Imprime un mensaje de error en la consola
            System.out.println(e.getMessage()); // Imprime el mensaje de la excepción en la consola
        }
        return conexion; // Retorna la conexión (puede ser nula si hay un error)
    }
}

