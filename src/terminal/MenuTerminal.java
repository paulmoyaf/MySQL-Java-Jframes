package terminal;

import java.io.IOException;
import java.util.EmptyStackException;
import java.util.InputMismatchException;
import java.util.Scanner;

import javax.swing.JOptionPane;

import formularios.FormListener;
import formularios.Formulario;
import querys.LecturaBBDD;

public class MenuTerminal {

    public static void menuTerminal() throws IOException{
        //String pathReadTeclado = "lib/BDTeclado.csv";
        boolean salir = false;
        Scanner in = new Scanner(System.in);

        System.out.println("PAUL'S MUSIC STORE");
        while(!salir){
            try {
                menuOpciones();
                String key2 = in.nextLine();
                ConsolaProducto.isVacio(key2);
                int key = Integer.parseInt(key2);      
                // "Añadir","Buscar por ID","Buscar Elemento en CSV","Cambiar","Borrar","Leer Info CSV","Eliminar Todo","Volver"};
                switch (key) {
                    case 1:
                        ConsolaProducto.addTeclados();
                        break;
                    case 2:
                        ConsolaProducto.buscaPorID();
                        break;
                    case 3:
                        ConsolaProducto.funcionActualizar();
                        break;
                    case 4:
                        ConsolaProducto.borraRegistro();
                        break;
                    case 5:
                        LecturaBBDD.listar();
                        break;
                    case 6:
                        System.out.println("Abriendo Interfaz Gráfica...");
                        abrirInterfaz();
                        salir = true;
                        break;                           
                    case 7:
                        System.out.println("ESKERRIK ASKO");
                        salir = true;
                        break;               
                    default:
                        limpiar();
                        System.out.println("Introduce una opción valida");
                }
            }catch (EmptyStackException e){
                // System.out.printf("\nEste codigo no existe....\n");
            }catch (InputMismatchException e) {
                limpiar();
                System.out.println("Debes insertar un número.\n");
                in.next();
            }
            catch (Exception e) {
                limpiar();
                System.out.printf("Error en el dato ingresado! Intentelo nuevamente...\n");
                // TODO: handle exception
            }
        }       
    }

    public static void limpiar() {  
        System.out.print("\033[H\033[2J");  
        System.out.flush();  
    }

    public static void abrirInterfaz() throws Exception{

        try {
            Formulario form = new Formulario();
            FormListener le = new FormListener();
            form.addWindowListener(le);
            form.setVisible(true);   
        } catch (Exception e) {
            System.out.println("Error conexión -> Revise ventana emergente para continuar!");
            JOptionPane.showMessageDialog(null, "Error con el servicio de conexión a la Base de Datos\n" + e, "Error Conexión", 0);
            MenuTerminal.menuTerminal();
        }
    }


    public static void menuOpciones() {
        String[] opciones = { "Añadir Producto", "Buscar por codigo ID", "Modificar",
                "Borrar", "Leer BBDD", "Abrir Interfaz Gráfica", "Salir" };
        System.out.print("\n***Menu de Opciones***\n");
        for (int i = 0; i < opciones.length; i++) {
            System.out.printf("%d - %s:\n", i + 1, opciones[i]);
        }
        System.out.print("Seleccione opción: ");
    }

}    
    

