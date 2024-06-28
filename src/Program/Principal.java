package Program;
// Importación de la clase FrmCliente del paquete Views
import Views.FrmCliente;
// Clase principal del programa
public class Principal {   
    // Método principal, punto de entrada del programa
    public static void main(String[] args) {
        // Creación de una nueva instancia de FrmCliente
        FrmCliente frmCliente = new FrmCliente();
        /* Establecer la posición de la ventana en el centro
          de la pantalla*/
        frmCliente.setLocationRelativeTo(null);
        
        // Hacer visible la ventana
        frmCliente.setVisible(true);
    }
}

