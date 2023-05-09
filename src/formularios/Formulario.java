package formularios;

/* Importamos las componentes Swing, así como el paquete con los interfaces para los eventos */
import javax.swing.*;

import conexion.Conexion;
import productos.Teclado;
import querys.LecturaBBDD;
import tabla.Tabla;
import terminal.MenuTerminal;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

/* La clase JFrame encapsula el concepto de una ventana, para implementar una aplicación que muestre una ventana debemos plantear una clase que herede de la clase JFrame e implemente a a ActionListener para el evento del botón*/
public class Formulario extends JFrame implements ActionListener {

  /* Definimos variables. */
  public static JTextField txt_id, txt_marca, txt_precio, txt_dcto, txt_tipo, txt_color, txt_teclas, txt_conector, txt_envio, txt_pvp, txt_code, txt_titulo;
  public JLabel lb_id, lb_marca, lb_precio, lb_dcto, lb_tipo, lb_color, lb_teclas, lb_conector, lb_envio, lb_pvp;
  public static JLabel lb_code, lb_temp, lb_recorreTemp;
  public static JButton btForward, btNext, btAdd, btBorrar, btCambiar, btBuscar, btOk, btCancel, btVerBB, btLimpiar, btClose;
  public JPanel p;

  private static Tabla tabla = null;

  String[] lista = {"PRIME", "REGULAR"};
  public static JComboBox<String> combo;
  
  static Conexion conexion = new Conexion();
  static ResultSet rs = null;

  /*
   * En el constructor de la clase llamamos al método heredado de la clase JFrame
   * llamado setLayout y le pasamos como parámetro un valor null, con esto estamos
   * informándole a la clase JFrame que utilizaremos posicionamiento absoluto para
   * los controles visuales dentro del JFrame
   */
  public Formulario() throws Exception {
  
    java.sql.Statement sql = null;
    Connection con = conexion.conectarMySQL();

    /* Configuración del JFrame */
    setLayout(null);
    setBounds(0, 0, 440, 600);
    setTitle("Music Store");
    setResizable(false);
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    this.setLocationRelativeTo(null);
    getContentPane().setBackground(Color.white);

    int x = -10;
    int y = 90;
    int yGap = 40;

    lb_code = new JLabel("Seleccione Codigo:");
    lb_code.setBounds(20, y-yGap, 180, 30);
    add(lb_code);

    /* Etiqueta de usuario */
    lb_id = new JLabel("Id:");
    lb_id.setBounds(x, y+(0 * yGap), 100, 30);
    lb_id.setHorizontalAlignment(SwingConstants.RIGHT);
    add(lb_id);

    lb_marca = new JLabel("Marca:");
    lb_marca.setBounds(x, y+(1 * yGap), 100, 30);
    lb_marca.setHorizontalAlignment(SwingConstants.RIGHT);
    add(lb_marca);

    lb_precio = new JLabel("Precio:");
    lb_precio.setBounds(x, y+(2 * yGap), 100, 30);
    lb_precio.setHorizontalAlignment(SwingConstants.RIGHT);
    add(lb_precio);

    lb_dcto = new JLabel("Descuento:");
    lb_dcto.setBounds(x, y+(3 * yGap), 100, 30);
    lb_dcto.setHorizontalAlignment(SwingConstants.RIGHT);
    add(lb_dcto);

    lb_tipo = new JLabel("Prime:");
    lb_tipo.setBounds(x, y+(4 * yGap), 100, 30);
    lb_tipo.setHorizontalAlignment(SwingConstants.RIGHT);
    add(lb_tipo);

    lb_color = new JLabel("Color:");
    lb_color.setBounds(x, y+(5 * yGap), 100, 30);
    lb_color.setHorizontalAlignment(SwingConstants.RIGHT);
    add(lb_color);

    lb_teclas = new JLabel("Teclas:");
    lb_teclas.setBounds(x, y+(6 * yGap), 100, 30);
    lb_teclas.setHorizontalAlignment(SwingConstants.RIGHT);
    add(lb_teclas);

    lb_conector = new JLabel("Conector:");
    lb_conector.setBounds(x, y+(7 * yGap), 100, 30);
    lb_conector.setHorizontalAlignment(SwingConstants.RIGHT);
    add(lb_conector);

    lb_envio = new JLabel("Envío:");
    lb_envio.setBounds(x, y+(8 * yGap), 100, 30);
    lb_envio.setHorizontalAlignment(SwingConstants.RIGHT);
    add(lb_envio);

    lb_pvp = new JLabel("PVP:");
    lb_pvp.setBounds(x, y+(9 * yGap), 100, 30);
    lb_pvp.setHorizontalAlignment(SwingConstants.RIGHT);
    add(lb_pvp);

    lb_temp = new JLabel("dato_temp");
    lb_temp.setBounds(300, y+(10 * yGap), 100, 30);
    add(lb_temp);
    lb_temp.setVisible(false);

    lb_recorreTemp = new JLabel("recorreTemp");
    lb_recorreTemp.setBounds(300, y+(11 * yGap), 100, 30);
    add(lb_recorreTemp);
    lb_recorreTemp.setVisible(false);


    txt_titulo = new JTextField();
    txt_titulo.setBounds(0, 0, 440, 38);
    txt_titulo.setBackground(Color.ORANGE);
    txt_titulo.setText("PAUL'S MUSIC STORE STOCK");
    txt_titulo.setFont(new Font("MONOSPACED",1,16));
    txt_titulo.setHorizontalAlignment(0);
    txt_titulo.setEditable(false);
    add(txt_titulo);

    txt_code = new JTextField();
    txt_code.setBounds(160, 55, 80, 20);
    add(txt_code);

    txt_id = new JTextField();
    txt_id.setBounds(100, 95, 150, 20);
    // txt_id.setEditable(false);
    add(txt_id);

    txt_marca = new JTextField();
    txt_marca.setBounds(100, 135, 150, 20);
    // txt_marca.setFocusable(false);
    add(txt_marca);

    txt_precio = new JTextField();
    txt_precio.setBounds(100, 175, 150, 20);
    // txt_precio.setFocusable(false);
    add(txt_precio);

    txt_dcto = new JTextField();
    txt_dcto.setBounds(100, 215, 150, 20);
    // txt_dcto.setFocusable(false);
    add(txt_dcto);

    txt_tipo = new JTextField();
    txt_tipo.setBounds(100, 255, 150, 20);
    // txt_tipo.setFocusable(false);
    // add(txt_tipo);

    combo = new JComboBox<String>(lista);
    combo.setBounds(100, 255, 150, 20);
    add(combo);

    txt_color = new JTextField();
    txt_color.setBounds(100, 295, 150, 20);
    // txt_color.setFocusable(false);
    add(txt_color);

    txt_teclas = new JTextField();
    txt_teclas.setBounds(100, 335, 150, 20);
    // txt_teclas.setFocusable(false);
    add(txt_teclas);

    txt_conector = new JTextField();
    txt_conector.setBounds(100, 375, 150, 20);
    // txt_conector.setFocusable(false);
    add(txt_conector);

    txt_envio = new JTextField();
    txt_envio.setBounds(100, 415, 150, 20);
    // txt_envio.setFocusable(false);
    add(txt_envio);

    txt_pvp = new JTextField();
    txt_pvp.setBounds(100, 455, 150, 20);
    // txt_pvp.setFocusable(false);
    add(txt_pvp);

 

    /* Botones */
    int ybt = 50;

    btBuscar = new JButton("Buscar");
    btBuscar.setBounds(300, ybt+(0 * yGap), 100, 30);
    btBuscar.setBackground(Color.DARK_GRAY);
    btBuscar.setForeground(Color.white);
    add(btBuscar);

    btForward = new JButton("Anterior");
    btForward.setBounds(300, ybt+(2 * yGap), 100, 30);
    btForward.setEnabled(false);
    add(btForward);

    btNext = new JButton("Siguiente");
    btNext.setBounds(300, ybt+(3 * yGap), 100, 30);
    add(btNext);

    btAdd = new JButton("Crear");
    btAdd.setBounds(300, ybt+(5 * yGap), 100, 30);
    add(btAdd);

    btCambiar = new JButton("Cambiar");
    btCambiar.setBounds(300, ybt+(6 * yGap), 100, 30);
    btCambiar.setVisible(false);
    add(btCambiar);

    btBorrar = new JButton("Borrar");
    btBorrar.setBounds(300, ybt+(7 * yGap), 100, 30);
    btBorrar.setVisible(false);
    add(btBorrar);

    btVerBB = new JButton("Mostrar BD");
    btVerBB.setBounds(300, ybt+(9 * yGap), 100, 30);
    btVerBB.setBackground(Color.orange);
    btVerBB.setForeground(Color.black);
    add(btVerBB);

    btLimpiar = new JButton("Limpiar");
    btLimpiar.setBounds(300, ybt+(10 * yGap), 100, 30);
    btLimpiar.setBackground(Color.LIGHT_GRAY);
    btLimpiar.setForeground(Color.black);
    add(btLimpiar);

    btClose = new JButton("Salir");
    btClose.setBounds(300, ybt+(11 * yGap), 100, 30);
    btClose.setBackground(Color.DARK_GRAY);
    btClose.setForeground(Color.white);
    add(btClose);

    btOk = new JButton("Aceptar");
    btOk.setBounds(40, ybt+(11 * yGap), 100, 30);
    btOk.setVisible(false);
    btOk.setBackground(Color.GREEN);
    btOk.setForeground(Color.white);
    add(btOk);    

    btCancel = new JButton("Cancelar");
    btCancel.setBounds(160, ybt+(11 * yGap), 100, 30);
    btCancel.setVisible(false);
    btCancel.setBackground(Color.red);
    btCancel.setForeground(Color.white);
    add(btCancel);




    /* Inicializar datos */
    Formulario.desahabilitarTXT();
    txt_envio.setEditable(false);
    txt_pvp.setEditable(false);


    /* Inicializo escuchador del botón */
    btOk.addActionListener(this);
    btCancel.addActionListener(this);
    btBuscar.addActionListener(this);
    btCambiar.addActionListener(this);
    btAdd.addActionListener(this);
    btBorrar.addActionListener(this);
    btVerBB.addActionListener(this);
    btLimpiar.addActionListener(this);
    btNext.addActionListener(this);
    btForward.addActionListener(this);
    btClose.addActionListener(this);
    

    sql = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
    System.out.println("Trabajando en Interfaz Gráfica ....");
    rs = sql.executeQuery("select * from instrumentos");
    rsVacia();
 
  }

//////////////////////////////////////////////////////////////////////////////////
  /* Método que implementa la acción del botón */


  public void actionPerformed(ActionEvent e) {

    if (e.getSource() == btOk) {
      if (lb_temp.getText().equals("crear")) {
        if(Formulario.crearItem()==true){
          try {
          // Formulario.crearItem();
          Formulario.habilitarTXT();
          Formulario.funcionLimpiar();
          //rsVacia();
          visibleBuscar();
          rs = null;
          JOptionPane.showMessageDialog(null, "Item creado correctamente\n", "Información", 1);
            // if (tabla.isShowing()) {
            //   tabla.dispose();
            //   showTable();
            // }
          } catch (Exception e1) {
            // TODO Auto-generated catch block
            //System.out.println("Notificacion: " + e1);
            e1.printStackTrace();
          }
        }        
      }
    
      if (lb_temp.getText().equals("cambiar")) {

        int in = JOptionPane.showOptionDialog(null, "Desea cambiar la información del item?", "Cambiar Información", 0,
            1, null, new Object[] { "CAMBIAR", "CANCELAR" }, null);

        if (in == 0) {
          if (Formulario.cambiarItem() == true) {
            // Formulario.cambiarItem();
            Formulario.funcionLimpiar();
            btBuscar.setVisible(true);
            rs = null;
            JOptionPane.showMessageDialog(null, "Item modificado correctamente\n", "Información", 1);
            // if (tabla.isShowing()) {
            //   tabla.dispose();
            //   showTable();
            // }
          }
        }
        if (in == 1) {
          Formulario.funcionCancelar();
        }

      }

      if (lb_temp.getText().equals("borrar")) {

        int in = JOptionPane.showOptionDialog(null, "Desea borrar la información del item?", "Cambiar Información", 0,
            1, null, new Object[] { "BORRAR", "CANCELAR" }, null);

        if (in == 0) {
          try {
            Formulario.borrarItem();
            Formulario.funcionLimpiar();
            //rsVacia();
            rs = null;
            JOptionPane.showMessageDialog(null, "Item eliminado correctamente\n", "Información", 1);
            // if (tabla.isShowing()) {
              //   tabla.dispose();
              //   showTable();
              // }
              
          } catch (Exception e1) {
            // TODO Auto-generated catch block
            //System.out.println("Notificacion: " + e1);
            e1.printStackTrace();
          }
        }
        if (in == 1) {
          Formulario.funcionCancelar();
        }
      }
    }
      
    

    if (e.getSource() == btAdd) {
      lb_temp.setText("crear");

      txt_code.setVisible(false);
      lb_code.setVisible(false);
      btBuscar.setVisible(false);

      btBorrar.setVisible(false);
      btCambiar.setVisible(false);
      btOk.setVisible(true);
      btCancel.setVisible(true);
      Formulario.limpiarTXT();
      Formulario.habilitarTXT();
      btForward.setEnabled(false);
      btNext.setEnabled(false);
                

    }

    if (e.getSource() == btBorrar) {
      lb_temp.setText("borrar");
      btOk.setVisible(true);
      btCancel.setVisible(true);
      btForward.setEnabled(false);
      btNext.setEnabled(false);
      btAdd.setVisible(false);
      btCambiar.setVisible(false);
      // btVerBB.setEnabled(false);
      // btLimpiar.setEnabled(false);
    }

    if (e.getSource() == btCambiar) {
      btOk.setVisible(true);
      btCancel.setVisible(true);
      lb_temp.setText("cambiar");
      Formulario.habilitarTXT();
      btForward.setEnabled(false);
      btNext.setEnabled(false);
      txt_code.setEnabled(false);
      btAdd.setVisible(false);
      btBorrar.setVisible(false);
      btBuscar.setVisible(false);
    }

    if (e.getSource() == btBuscar) {
      // try {
        Formulario.desahabilitarTXT();
        btBorrar.setVisible(true);
        btCambiar.setVisible(true);
        btForward.setEnabled(false);
        btNext.setEnabled(false);
        btAdd.setVisible(true);
        Formulario.buscarItem();
      // } catch (Exception ex) {
      //   JOptionPane.showMessageDialog(null, "Error con el servicio de conexión a la Base de Datos\n" + e, "Error Conexión", 0);
      // }
    }

    if (e.getSource() == btLimpiar) {
      Formulario.funcionLimpiar();
      rs=null;
    }

   if (e.getSource() == btClose) {
      JOptionPane.showMessageDialog(null, "BYE BYE !!!\n", "Información", 1);
      dispose();
      try {
        MenuTerminal.menuTerminal();
      } catch (IOException e1) {
        // TODO Auto-generated catch block
        e1.printStackTrace();
      }
    }

    if (e.getSource() == btVerBB) {
      // showTable();
      mostrarFormTabla();
      dispose();
    }


    if (e.getSource() == btCancel) {
      Formulario.funcionCancelar();
    }

    if (e.getSource() == btNext) {

      if(rs==null){
        try {
          
          Formulario.newConexion();
        } catch (ClassNotFoundException | SQLException e1) {
          // TODO Auto-generated catch block
          
          System.out.println("No hay elementos." + e1);
          //e1.printStackTrace();
        }
      }

      btAdd.setVisible(true);
      btCambiar.setVisible(true);
      btBorrar.setVisible(true);
      Formulario.nextItem();
      lb_recorreTemp.setText(txt_id.getText());
      txt_code.setText("");    
   }

   if (e.getSource() == btForward) {

    if(rs==null){
      try {
        Formulario.newConexion();
      } catch (ClassNotFoundException | SQLException e1) {
        // TODO Auto-generated catch block
        System.out.println("No hay elementos." + e1);
        //e1.printStackTrace();
      }
    }

    btAdd.setVisible(true);
    btCambiar.setVisible(true);
    btBorrar.setVisible(true);
    Formulario.previousItem();
    lb_recorreTemp.setText(txt_id.getText());
    txt_code.setText("");      
    }
     
  }

  private static void showTable(){
    try {
      // VerBBDD.listar();
      tabla = new Tabla();
      tabla.setLocationRelativeTo(null);
      tabla.setVisible(true);
      tabla.setSize(800, 500);
      // tabla.setExtendedState(MAXIMIZED_BOTH);
    } catch (ClassNotFoundException e1) {
      // TODO Auto-generated catch block
      //e1.printStackTrace();
      System.out.println("El form de la tabla no esta abierto: " + e1);
    } catch (Exception e1) {
      // TODO Auto-generated catch block
      //e1.printStackTrace();
      System.out.println("El form de la tabla no esta abierto: " + e1);
    }
  }

  public static void mostrarFormTabla() {
    FormTabla form;
    try {
      form = new FormTabla();
      FormListener le = new FormListener();
      form.addWindowListener(le);
      form.setVisible(true);
    } catch (Exception e1) {
      // TODO Auto-generated catch block
      e1.printStackTrace();
    }
  }

  public static void rsVacia() throws SQLException{
    if(!rs.isBeforeFirst()){
      noVisibleBuscar();

    }
    else{

      visibleBuscar();
      // btCambiar.setVisible(false);
      // btBorrar.setVisible(false);
    }
  }

  
  public static void funcionCancelar(){
    txt_code.setVisible(true);
    lb_code.setVisible(true);
    btBuscar.setVisible(true);

    btBorrar.setVisible(false);
    btCambiar.setVisible(false);
    btOk.setVisible(false);
    btForward.setEnabled(false);
    btNext.setEnabled(true);
    Formulario.limpiarTXT();
    Formulario.desahabilitarTXT();
    btCancel.setVisible(false);
    txt_code.setEnabled(true);
    rs=null;
  }

  public static void visibleBuscar(){
    lb_code.setVisible(true);
    txt_code.setVisible(true);
    btBuscar.setVisible(true);
    btForward.setVisible(true);
    btNext.setVisible(true);
    // btCambiar.setVisible(true);
    // btBorrar.setVisible(true);
  }

  public static void noVisibleBuscar(){
    lb_code.setVisible(false);
    txt_code.setVisible(false);
    btBuscar.setVisible(false);
    btForward.setVisible(false);
    btNext.setVisible(false);
    // btCambiar.setVisible(false);
    // btBorrar.setVisible(false);
    
  }

  public static void funcionLimpiar(){
    txt_code.setEnabled(true);
    btBorrar.setVisible(false);
    btCambiar.setVisible(false);
    btOk.setVisible(false);
    btCancel.setVisible(false);
    btForward.setEnabled(false);
    btNext.setEnabled(true);
    btAdd.setVisible(true);
    Formulario.limpiarTXT();
    Formulario.desahabilitarTXT();

  }

  public static void limpiarTXT(){
    txt_code.setText(null);
    txt_id.setText(null);
    txt_marca.setText(null);
    txt_precio.setText(null);
    txt_dcto.setText(null);
    txt_tipo.setText(null);
    txt_color.setText(null);
    txt_teclas.setText(null);
    txt_conector.setText(null);
    txt_envio.setText(null);
    txt_pvp.setText(null);
    // lb_temp.setText("dato_temp");
  }

  public static void habilitarTXT(){
    txt_id.setEditable(true);
    txt_marca.setEditable(true);
    txt_precio.setEditable(true);
    txt_dcto.setEditable(true);
    txt_tipo.setEditable(true);
    combo.setEnabled(true);
    txt_color.setEditable(true);
    txt_teclas.setEditable(true);
    txt_conector.setEditable(true);
  }

  public static void desahabilitarTXT(){
    txt_id.setEditable(false);
    txt_marca.setEditable(false);
    txt_precio.setEditable(false);
    txt_dcto.setEditable(false);
    txt_tipo.setEditable(false);
    combo.setEnabled(false);
    txt_color.setEditable(false);
    txt_teclas.setEditable(false);
    txt_conector.setEditable(false);
  }

  private static void buscarItem() {
    try {
      txt_id.setText(LecturaBBDD.buscarItem(txt_code.getText()).getId());
      txt_marca.setText(LecturaBBDD.buscarItem(txt_code.getText()).getMarca());
      txt_precio.setText(Double.toString(LecturaBBDD.buscarItem(txt_code.getText()).getPrecio()));
      txt_dcto.setText(String.valueOf(LecturaBBDD.buscarItem(txt_code.getText()).getDcto()));
      txt_tipo.setText(LecturaBBDD.buscarItem(txt_code.getText()).isPrime());
      combo.setSelectedItem(LecturaBBDD.buscarItem(txt_code.getText()).isPrime());
      txt_color.setText(LecturaBBDD.buscarItem(txt_code.getText()).getColor());
      txt_teclas.setText(Integer.toString(LecturaBBDD.buscarItem(txt_code.getText()).getTeclas()));
      txt_conector.setText(LecturaBBDD.buscarItem(txt_code.getText()).getConector());
      txt_envio.setText(LecturaBBDD.buscarItem(txt_code.getText()).getEnvio());
      txt_pvp.setText(Double.toString(LecturaBBDD.buscarItem(txt_code.getText()).getPrecioVenta()));
     
    } catch (Exception e1) {
      System.out.println("no existe");
      Formulario.funcionLimpiar();
      e1.printStackTrace();
    }
  }


  private static void verItem() {
    try {
      
      txt_id.setText(LecturaBBDD.primerItem().getId());
      txt_marca.setText(LecturaBBDD.primerItem().getMarca());
      txt_precio.setText(Double.toString(LecturaBBDD.primerItem().getPrecio()));
      txt_dcto.setText(Double.toString(LecturaBBDD.primerItem().getDcto()));
      txt_tipo.setText(LecturaBBDD.primerItem().isPrime());
      combo.setSelectedItem(LecturaBBDD.primerItem().isPrime());
      txt_color.setText(LecturaBBDD.primerItem().getColor());
      txt_teclas.setText(Integer.toString(LecturaBBDD.primerItem().getTeclas()));
      txt_conector.setText(LecturaBBDD.primerItem().getConector());
      txt_envio.setText(LecturaBBDD.primerItem().getEnvio());
      txt_pvp.setText(Double.toString(LecturaBBDD.primerItem().getPrecioVenta()));

    } catch (Exception e1) {
      // TODO Auto-generated catch block
      e1.printStackTrace();
    }
  }

  
  private static void previousItem() {
    try {
      Teclado teclado = new Teclado();
      teclado = LecturaBBDD.previousItem(rs);
      if(teclado!=null){
      btNext.setEnabled(true);
      txt_id.setText(teclado.getId());
      txt_marca.setText(teclado.getMarca());
      txt_precio.setText(Double.toString(teclado.getPrecio()));
      txt_dcto.setText(Double.toString(teclado.getDcto()));
      txt_tipo.setText(teclado.isPrime());
      combo.setSelectedItem(teclado.isPrime());
      txt_color.setText(teclado.getColor());
      txt_teclas.setText(Integer.toString(teclado.getTeclas()));
      txt_conector.setText(teclado.getConector());
      txt_envio.setText(teclado.getEnvio());
      txt_pvp.setText(Double.toString(teclado.getPrecioVenta()));
      }else{
        btForward.setEnabled(false);
        btNext.setEnabled(true);
      }
    } catch (Exception e1) {
      // TODO Auto-generated catch block
      System.out.println("No hay elementos." + e1);
      try {
        rsVacia();
        btCambiar.setVisible(false);
        btBorrar.setVisible(false);
      } catch (SQLException e) {
        // TODO Auto-generated catch block
        //e.printStackTrace();
      }
      //e1.printStackTrace();
    }
  }

  private static void nextItem() {
    try {
      Teclado teclado = new Teclado();
      teclado = LecturaBBDD.nextItem(rs);
      if(teclado!=null){
      btForward.setEnabled(true);
      txt_id.setText(teclado.getId());
      txt_marca.setText(teclado.getMarca());
      txt_precio.setText(Double.toString(teclado.getPrecio()));
      txt_dcto.setText(Double.toString(teclado.getDcto()));
      txt_tipo.setText(teclado.isPrime());
      combo.setSelectedItem(teclado.isPrime());
      txt_color.setText(teclado.getColor());
      txt_teclas.setText(Integer.toString(teclado.getTeclas()));
      txt_conector.setText(teclado.getConector());
      txt_envio.setText(teclado.getEnvio());
      txt_pvp.setText(Double.toString(teclado.getPrecioVenta()));
      }else{
        btForward.setEnabled(true);
        btNext.setEnabled(false);
      }


    } catch (Exception e1) {
      // TODO Auto-generated catch block
      System.out.println("No hay elementos." + e1);
      try {
        rsVacia();
        btCambiar.setVisible(false);
        btBorrar.setVisible(false);
      } catch (SQLException e) {
        // TODO Auto-generated catch block
        //e.printStackTrace();
      }
      //e1.printStackTrace();
    }
  }

  public static boolean cambiarItem(){
    boolean bool = false;
    try {
      if(txt_code.getText().equals("")){
        txt_code.setText(lb_recorreTemp.getText());
      }
      bool = LecturaBBDD.cambiarItem(
        txt_code.getText(), txt_id.getText(), txt_marca.getText(),  Double.parseDouble(txt_precio.getText()), 
        Double.parseDouble(txt_dcto.getText()), String.valueOf(combo.getSelectedItem()), txt_color.getText(),  Integer.parseInt(txt_teclas.getText()),
        txt_conector.getText());
    } catch (Exception e1) {
      // TODO Auto-generated catch block
      e1.printStackTrace();
      JOptionPane.showMessageDialog(null, "Error, verifica que esten bien ingresados los datos\n" + e1,"Error de Datos", 0);
      bool = false;
    }
    return bool;
  }

  public static boolean crearItem() {
    boolean bool = false;
    
    try {
        Double total = Double.parseDouble(txt_precio.getText())
            - ((Double.parseDouble(txt_precio.getText()) * Double.parseDouble(txt_dcto.getText())) / 100);

          if (txt_tipo.getText().equals("PRIME")) {
            txt_envio.setText("GRATIS");
            txt_pvp.setText(String.valueOf(total));
          } else {
            txt_envio.setText("+15€");
            txt_pvp.setText(String.valueOf(total + 15));
          }

          bool = LecturaBBDD.crearItem(
              txt_id.getText(), txt_marca.getText(), Double.parseDouble(txt_precio.getText()),
              Double.parseDouble(txt_dcto.getText()), String.valueOf(combo.getSelectedItem()), txt_color.getText(),
              Integer.parseInt(txt_teclas.getText()),
              txt_conector.getText());

          } catch (Exception e1) {
            e1.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error, verifica que esten bien ingresados los datos\n" + e1,
            "Error de Datos", 0);
          }
    return bool;
  }

  public static void borrarItem(){
    try {
      if(txt_id.getText().equals(lb_recorreTemp.getText())){
        txt_code.setText(txt_id.getText());
      }
      LecturaBBDD.borrarItem(txt_code.getText());
    } catch (Exception e1) {
      // TODO Auto-generated catch block
      e1.printStackTrace();
    }
  }

  public static void newConexion() throws ClassNotFoundException, SQLException{
    rs = null;
    System.out.println("rs null");
    java.sql.Statement sql = null;
    Connection con = conexion.conectarMySQL();

    sql = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
    System.out.println("rs nueva");
    rs = sql.executeQuery("select * from instrumentos");
  }


}





