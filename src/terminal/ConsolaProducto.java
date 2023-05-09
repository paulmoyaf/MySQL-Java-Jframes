package terminal;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.Scanner;

import productos.*;
import querys.LecturaBBDD;


public class ConsolaProducto {


    public static void addTeclados() throws IOException {

        try {
            String key;
            boolean keyBoolean = false;

            ArrayList<Teclado> arrTeclado = new ArrayList<Teclado>();
            Teclado teclado = new Teclado();

            Scanner in = new Scanner(System.in);
            String[] opc = { "Numero de id", "Marca", "Precio", "Descuento", "tipo de Prime", "Color",
                    "Numero de Teclas", "Tipo de Conector" };

            System.out.printf("Ingrese %s [t=codigo de teclado]: t", opc[0]);
            key = "t" + in.nextLine();
            isVacio(key);
            teclado = LecturaBBDD.buscarItem(key);

            if (teclado.getId() != null) {
                System.out.print("Código duplicado, ingrese otro distinto...\n");
            } else {
                teclado.setId(key);

                System.out.printf("Ingrese %s: ", opc[1]);
                key = in.nextLine();
                isVacio(key);
                teclado.setMarca(key);

                System.out.printf("Ingrese %s: ", opc[2]);
                key = in.nextLine();
                isMenor0(key);
                teclado.setPrecio(Double.parseDouble(key));

                System.out.printf("Ingrese %s: ", opc[3]);
                key = in.nextLine();
                isMenor0(key);
                is100(key);
                teclado.setDcto(Double.parseDouble(key));

                System.out.printf("Ingrese %s (s/n) >> s=Prime / n=Regular Product: ", opc[4]);
                key = in.nextLine();
                isVacio(key);
                if (key.equalsIgnoreCase("s")) {
                    keyBoolean = true;
                }
                teclado.setPrime(keyBoolean);

                System.out.printf("Ingrese %s: ", opc[5]);
                key = in.nextLine();
                isVacio(key);
                teclado.setColor(key);

                System.out.printf("Ingrese %s: ", opc[6]);
                key = in.nextLine();
                isVacio(key);
                isMenor0(key);
                teclado.setTeclas(Integer.parseInt(key));

                System.out.printf("Ingrese %s: ", opc[7]);
                key = in.nextLine();
                isVacio(key);
                teclado.setConector(key);

                System.out.println("\nDatos del Producto ingresado:");
                // teclado.mostrarInfo();
                LecturaBBDD.cuteTitulo();
                teclado.toStringCute();
                // System.out.println(teclado.toStringDatos());

                arrTeclado.add(teclado);

                System.out.print(
                        "\nDesea grabar la informacion en el Fichero CSV?:\n- Presione S para grabar\n- Presione otra tecla para ingresar los valores nuevamente\n- Presione N para No Guardar y SALIR: ");
                key = in.nextLine();
                isVacio(key);
                if (key.equalsIgnoreCase("s")) {
                    try {

                        LecturaBBDD.crearItem(teclado.getId(), teclado.getMarca(), teclado.getPrecio(),
                                teclado.getDcto(), teclado.isPrime(), teclado.getColor(), teclado.getTeclas(),
                                teclado.getConector());
                        // MenuTerminal.limpiar();
                        System.out.print("\nProducto creado!!!\n");

                    } catch (IOException e) {
                        System.out.println(e);

                    } catch (Exception e) {
                        // TODO: handle exception
                    }
                } else if (key.equalsIgnoreCase("n")) {
                    MenuTerminal.limpiar();

                } else {
                    MenuTerminal.limpiar();
                    addTeclados();
                }
            }

            // addTeclados(pathTeclado);
        } catch (EmptyStackException e) {
            ConsolaProducto.addTeclados();
        
        } catch (Exception e) {
            System.out.printf("Error en el dato ingresado! Intentelo nuevamente...\n");
            // System.err.printf("Error en el dato ingresado! Intentelo nuevamente...\n");
            addTeclados();
            // TODO: handle exception
        }

    }

    public static void funcionActualizar() throws FileNotFoundException {

        try {
            buscaCambiaActualiza();
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    public static void borraRegistro() throws FileNotFoundException {
        try {
            LecturaBBDD.listar();
            Teclado tecladoFind = new Teclado();
            Scanner in = new Scanner(System.in);
            int i = 0;
            System.out.print("\nIngrese codigo para eliminar: ");
            String keyCode = in.nextLine();
            isVacio(keyCode);

            tecladoFind = LecturaBBDD.buscarItem(keyCode);

            if (tecladoFind.getId() == null) {
                System.out.print("Este código no existe...\n");
            } else {
            
            System.out.print("\nEl elemento es:\n");
            LecturaBBDD.cuteTitulo();
            tecladoFind.toStringCute();

            System.out.print("\nEsta seguro que desea eliminar el registro? (s/n): ");
            String key = in.nextLine();
            isVacio(key);

            if (key.equalsIgnoreCase("s")) {
                LecturaBBDD.borrarItem(keyCode);
                System.out.println("\nEl elemento ha sido eliminado\n");
            }
            else{
                System.out.println("\nSaliendo menu borrar...\n");
            }
        }

        }catch (EmptyStackException e){
            borraRegistro();
        
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    public static void buscaPorID() throws FileNotFoundException {
        try {

            LecturaBBDD.listar();
            Scanner in = new Scanner(System.in);
            Teclado tecladoFind = new Teclado();
            int i = 0;
            System.out.print("\nIngrese el codigo que busca: ");
            String key = in.nextLine();
            isVacio(key);
            tecladoFind = LecturaBBDD.buscarItem(key);
            if (tecladoFind.getId() == null) {
                System.out.print("Este código no existe...\n");
                // buscaPorID();
            } else {
                // LecturaBBDD.buscarItem(key);
                // tecladoFind.mostrarInfo();
                System.out.println("\nEUSKERA:\n" + tecladoFind.toStringAbstract("EUS"));
                System.out.println("\nENGLISH:\n" + tecladoFind.toStringAbstract("EN"));
                System.out.println("\nCASTELLANO:\n" + tecladoFind.toStringAbstract("ES"));
                
                LecturaBBDD.cuteTitulo();
                tecladoFind.toStringCute();
            }
        } catch (EmptyStackException e) {
            // System.out.printf("\nEste codigo no existe....\n");
        } catch (Exception e) {
            System.out.printf("\nEste codigo no existe....\n");
        }
    }

    private static void buscaCambiaActualiza() throws IOException {

        try {
            Teclado tecladoFind = new Teclado();

            LecturaBBDD.listar();

            Scanner in = new Scanner(System.in);
            int i = 0;
            System.out.print("\nIngrese codigo para modificar: ");
            String keyCode = in.nextLine();
            isVacio(keyCode);

            tecladoFind = LecturaBBDD.buscarItem(keyCode);

            if (tecladoFind.getId() == null) {
                System.out.print("Este código no existe...\n");
            } else{

            LecturaBBDD.cuteTitulo();
            tecladoFind.toStringCute();

            System.out.println("\nMenu de Cambio de Valores");
            String[] cambios = { "Marca", "Precio", "Descuento", "Prime", "Color", "Teclas", "Conector" };
            for (int j = 0; j < cambios.length; j++) {
                System.out.printf("%d - %s:\n", j + 1, cambios[j]);
            }

            boolean salir = false;
            while (!salir) {
                try {

                    System.out.print("\nQue valor desea cambiar? ");
                    String key = in.nextLine();
                    isVacio(key);

                    int op = Integer.parseInt(key);
                    String keychange;

                    switch (op) {
                        case 1: // MARCA
                            System.out.printf("\nValor anterior: %s", tecladoFind.getMarca());
                            System.out.print("\nIngrese nuevo valor: ");
                            keychange = in.nextLine();
                            isVacio(keychange);
                            tecladoFind.setMarca(keychange);
                            System.out.printf("\nValor Nuevo: %s", tecladoFind.getMarca());
                            tecladoFind.mostrarInfo();
                            salir = true;
                            break;
                        case 2: // PRECIO
                            System.out.printf("\nValor anterior: %s", tecladoFind.getPrecio());
                            System.out.print("\nIngrese nuevo valor: ");
                            keychange = in.nextLine();
                            isVacio(keychange);
                            isMenor0(keychange);
                            tecladoFind.setPrecio(Double.parseDouble(keychange));
                            System.out.printf("\nValor Nuevo: %s", tecladoFind.getPrecio());
                            tecladoFind.mostrarInfo();
                            salir = true;
                            break;
                        case 3: // DESCUENTO
                            System.out.printf("\nValor anterior: %s", tecladoFind.getDcto());
                            System.out.print("\nIngrese nuevo valor: ");
                            keychange = in.nextLine();
                            isVacio(keychange);
                            is100(keychange);
                            isMenor0(keychange);
                            tecladoFind.setDcto(Double.parseDouble(keychange));
                            System.out.printf("\nValor Nuevo: %s", tecladoFind.getDcto());
                            tecladoFind.mostrarInfo();
                            salir = true;
                            break;
                        case 4: // PRIME
                            System.out.printf("\nValor anterior: %s", tecladoFind.isPrime());
                            System.out.print("\nIngrese (s/n) >> s=Prime / n=Regular Product: ");
                            keychange = in.nextLine();
                            isVacio(keychange);
                            boolean keyBoolean = false;
                            if (keychange.equalsIgnoreCase("s")) {
                                keyBoolean = true;
                            }
                            tecladoFind.setPrime(keyBoolean);
                            System.out.printf("\nValor Nuevo: %s", tecladoFind.isPrime());
                            tecladoFind.mostrarInfo();
                            salir = true;
                            break;
                        case 5: // COLOR
                            System.out.printf("\nValor anterior: %s", tecladoFind.getColor());
                            System.out.print("\nIngrese nuevo valor: ");
                            keychange = in.nextLine();
                            isVacio(keychange);
                            tecladoFind.setColor(keychange);
                            System.out.printf("\nValor Nuevo: %s", tecladoFind.getColor());
                            tecladoFind.mostrarInfo();
                            salir = true;
                            break;
                        case 6: // TECLAS
                            System.out.printf("\nValor anterior: %s", tecladoFind.getTeclas());
                            System.out.print("\nIngrese nuevo valor: ");
                            keychange = in.nextLine();
                            isVacio(keychange);
                            isMenor0(keychange);
                            tecladoFind.setTeclas(Integer.parseInt(keychange));
                            System.out.printf("\nValor Nuevo: %s", tecladoFind.getTeclas());
                            tecladoFind.mostrarInfo();
                            salir = true;
                            break;
                        case 7: // CONECTOR
                            System.out.printf("\nValor anterior: %s", tecladoFind.getConector());
                            System.out.print("\nIngrese nuevo valor: ");
                            keychange = in.nextLine();
                            isVacio(keychange);
                            tecladoFind.setConector(keychange);
                            System.out.printf("\nValor Nuevo: %s", tecladoFind.getConector());
                            tecladoFind.mostrarInfo();
                            salir = true;
                            break;

                        default:
                            System.out.printf("Seleccione una opción válida");
                            // break;
                    }

                } catch (Exception e) {
                    System.out.printf("Valor incorrecto, ingrese nuevamente");
                }
            }

            System.out.print(
                    "\nDesea grabar la informacion en el Fichero CSV?:\n- Presione S para grabar\n- Presione otra tecla para modificar producto nuevamente\n- Presione N para No Guardar y SALIR: ");
            String key2 = in.nextLine();
            isVacio(key2);
            if (key2.equalsIgnoreCase("s")) {
                try {
                    LecturaBBDD.cambiarItem(keyCode, tecladoFind.getId(), tecladoFind.getMarca(),
                            tecladoFind.getPrecio(), tecladoFind.getDcto(), tecladoFind.isPrime(),
                            tecladoFind.getColor(), tecladoFind.getTeclas(), tecladoFind.getConector());

                    // MenuTerminal.limpiar();
                    System.out.print("\nInformación guardada satisfactoriamente ... \n");

                } catch (IOException e) {
                    System.out.println(e);

                } catch (Exception e) {
                    // TODO: handle exception
                }
            } else if (key2.equalsIgnoreCase("n")) {
                MenuTerminal.limpiar();
                MenuTerminal.menuTerminal();
            } else {
                MenuTerminal.limpiar();
                ConsolaProducto.funcionActualizar();            }
        }

        } catch (EmptyStackException e) {
            // ConsolaProducto.addTeclados();
        } catch (IOException e) {
            System.out.println(e);

        } catch (Exception e) {
            // MenuTerminal.limpiar();
            // System.out.printf("\nDato erroneo....\n");
        }

    }

    public static void isVacio(String input) {
        if (input.isEmpty() || input.equalsIgnoreCase("t") || input.equalsIgnoreCase("g")) {
            System.out.println("No ha ingresado ningun valor");
            throw new EmptyStackException();
        }
    }

    static void is100(String input) {
        double dcto = Double.parseDouble(input);
        if (dcto < 0 || dcto > 100) {
            throw new EmptyStackException();
        }
    }

    static void isMenor0(String input) {
        double dcto = Double.parseDouble(input);
        if (dcto < 0) {
            throw new EmptyStackException();
        }
    }


    private static boolean buscaIDAutomatico(ArrayList arrTeclados, String key) {
        boolean existeID = false;
        try {
            Teclado tecladoFind = new Teclado();
            int i = 0;

            while (i < arrTeclados.size() && !((Producto) arrTeclados.get(i)).getId().equalsIgnoreCase(key)) {
                i++;
            }

            if (!((Producto) arrTeclados.get(i)).getId().equalsIgnoreCase(key)) {
                // Aqui salta si el codigo no existe
            } else if (i < arrTeclados.size()) {
                tecladoFind = (Teclado) arrTeclados.get(i);
            }
            System.out.println("Este codigo ya existe en el CSV:");
            tecladoFind.mostrarInfo();
            System.out.println("");
            existeID = true;
            // addTeclados(pathTeclado);
        } catch (Exception e) {
            // System.out.printf("\nEste codigo no existe....\n");
        }
        return existeID;
    }

}