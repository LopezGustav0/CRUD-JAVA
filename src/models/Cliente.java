package models;

// Importación de las clases JDBC necesarias
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

// Definición de la clase Cliente
public class Cliente {
    // Atributos de la clase Cliente
    private int id;
    private String nombre;
    private String numruc;
    private String direccion;
    private String telefono;
    //Encapsulamiento
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getNumruc() {
        return numruc;
    }
    public void setNumruc(String numruc) {
        this.numruc = numruc;
    }
    public String getDireccion() {
        return direccion;
    }
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    public String getTelefono() {
        return telefono;
    }
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    // Constructor predeterminado
    public Cliente() {
    }
    // Constructor con parámetros
    public Cliente(int id, String nombre, String numruc, String direccion, String telefono) {
        this.id = id;
        this.nombre = nombre;
        this.numruc = numruc;
        this.direccion = direccion;
        this.telefono = telefono;
    }

    // Métodos getter y setter para cada atributo
    

    // Método para buscar un cliente por su ID en la base de datos
    public Cliente getBuscarById(int idBuscar) {
        Cliente cliente = new Cliente(); // Instancia de Cliente para almacenar los datos encontrados
        try{
            Connection cnx = Conexion.getConexion();// Establecer la conexión
            // Preparar la instrucción SQL
            PreparedStatement ps = cnx.prepareStatement("select * from cliente where id=?");
            ps.setInt(1, idBuscar);// Pasar los valores de los parámetros SQL
            ResultSet rs = ps.executeQuery(); // Ejecutar la instrucción SQL
            System.out.println("Busqueda exitosa");
            if (rs.next()) {// Desplazar el puntero de registros
                // Leer los valores de la fila y asignarlos al objeto cliente
                cliente.setId(rs.getInt("id"));
                cliente.setNombre(rs.getString("nombre"));
                cliente.setNumruc(rs.getString("numruc"));
                cliente.setDireccion(rs.getString("direccion"));
                cliente.setTelefono(rs.getString("telefono"));
            } else {// Si no se encontraron registros, se establecen valores predeterminados
                cliente.setId(0);
                cliente.setNombre("");
                cliente.setNumruc("");
                cliente.setDireccion("");
                cliente.setTelefono("");
            }
        } catch (Exception e) {// Si ocurre un error, se establecen valores y se imprime el mensaje de error  
            cliente.setId(-1);
            cliente.setNombre("-Error de conexion-");
            cliente.setNumruc("");
            cliente.setDireccion("");
            cliente.setTelefono("");
        }
        return cliente;// Retornar el objeto cliente
    }

    // Método para insertar un nuevo cliente en la base de datos
    public int setInsertar(Cliente cliente) {
        int nuevoId;
        try {
            // Establecer la conexión
            Connection cnx = Conexion.getConexion();
            // Preparar la instrucción SQL para insertar un nuevo cliente
            PreparedStatement ps = cnx.prepareStatement("insert into cliente ( nombre,numruc,direccion,telefono)values(?,?,?,?);");
            // Pasar los valores de los parámetros SQL
            ps.setString(1, cliente.getNombre());
            ps.setString(2, cliente.getNumruc());
            ps.setString(3, cliente.getDireccion());
            ps.setString(4, cliente.getTelefono());
            // Ejecutar la instrucción SQL de inserción
            ps.executeUpdate(); // INSERT
            // Leer el nuevo ID generado
            ps = cnx.prepareStatement("select max(id) as nuevoId from cliente");
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
                nuevoId =  rs.getInt("nuevoId");
                System.out.println("Insercion correcta ");
            } else {
                nuevoId = 0; // Si no se encuentra un nuevo ID, se establece como 0
            }
        } catch (Exception e) {
            nuevoId = -1; // Si ocurre un error, se establece como -1
        }
        // Retornar el nuevo ID
        return nuevoId;        
    }

    // Método para actualizar un cliente en la base de datos
    public void setActualizar(Cliente cliente) {
        try {
            // Establecer la conexión
            Connection cnx = Conexion.getConexion();
            // Preparar la instrucción SQL para actualizar los datos del cliente
            PreparedStatement ps = cnx.prepareStatement("update cliente set nombre=?,numruc=?,direccion=?,telefono=? where id=?;");
            // Pasar los valores de los parámetros SQL
            ps.setString(1, cliente.getNombre());
            ps.setString(2, cliente.getNumruc());
            ps.setString(3, cliente.getDireccion());
            ps.setString(4, cliente.getTelefono());
            ps.setInt(5, cliente.getId());
            ps.executeUpdate(); // INSERT-UPDATE-DELETE
            System.out.println("Actualizacion correcta");
        } catch (Exception e) {
            // Manejo de errores
        }
    }

    // Método para eliminar un cliente de la base de datos por su ID
    public void setEliminar(int idEliminar) {
        try {
            // Establecer la conexión
            Connection cnx = Conexion.getConexion();
            // Preparar la instrucción SQL para eliminar el cliente
            PreparedStatement ps = cnx.prepareStatement("delete from cliente where id=?;");
            // Pasar los valores de los parámetros SQL
            ps.setInt(1, idEliminar);
            ps.executeUpdate(); // INSERT-UPDATE-DELETE
            System.out.println("Eliminacion correcta");
        } catch (Exception e) {
            // Manejo de errores
        }
    }
}
