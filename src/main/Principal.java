package main;

import java.sql.Connection;
import javax.swing.JOptionPane;

import conexion.Conexion;
import terminal.MenuTerminal;

public class Principal {
    public static void main(String[] args) throws Exception {
        
        try {
            Conexion conexion = new Conexion();
            Connection con = conexion.conectarMySQL();
            java.sql.Statement sql = con.createStatement();
            MenuTerminal.menuTerminal();

            }catch (Exception e) {
                System.out.println("Error conexión Base de Datos -> Revise ventana emergente para continuar!");
                JOptionPane.showMessageDialog(null, "Error con el servicio de conexión a la Base de Datos\n"
                + "Active el servicio y reinicie el programa\n", "Error Conexión", 0);    
            //e.printStackTrace();
        }
    
    }
}
