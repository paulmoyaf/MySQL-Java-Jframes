package formularios;

/* Importamos las componentes Swing, así como el paquete con los interfaces para los eventos */
import javax.swing.*;

import conexion.Conexion;
import querys.LecturaBBDD;
import tabla.Tabla;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

/* La clase JFrame encapsula el concepto de una ventana, para implementar una aplicación que muestre una ventana debemos plantear una clase que herede de la clase JFrame e implemente a a ActionListener para el evento del botón*/
public class FormAdd extends JFrame implements ActionListener {

  /* Definimos variables. */
  // public static JTextArea txt_id, txt_marca, txt_precio, txt_dcto, txt_tipo, txt_color, txt_teclas, txt_conector, txt_envio, txt_pvp, txt_code;
  public static JTextField txt_id, txt_marca, txt_precio, txt_dcto, txt_tipo, txt_color, txt_teclas, txt_conector, txt_envio, txt_pvp,txt_titulo;
  public JLabel lb_id, lb_marca, lb_precio, lb_dcto, lb_tipo, lb_color, lb_teclas, lb_conector, lb_envio, lb_pvp;
  public static JLabel lb_info, lb_temp, lb_recorreTemp;
  public static JButton btOk, btCancel,btLimpiar;
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
  public FormAdd() throws Exception {
  
    java.sql.Statement sql = null;
    Connection con = conexion.conectarMySQL();

    /* Configuración del JFrame */
    setLayout(null);
    setBounds(0, 0, 300, 620);
    setTitle("Music Store");
    setResizable(false);
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    this.setLocationRelativeTo(null);
    getContentPane().setBackground(Color.white);

    int x = -10;
    int y = 90;
    int yGap = 40;

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
    txt_titulo.setBounds(0, 0, 300, 80);
    txt_titulo.setBackground(Color.DARK_GRAY);
    txt_titulo.setForeground(Color.white);
    txt_titulo.setText("CREAR PRODUCTO");
    txt_titulo.setFont(new Font("MONOSPACED",1,16));
    txt_titulo.setHorizontalAlignment(0);
    txt_titulo.setEditable(false);
    add(txt_titulo);


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

    btLimpiar = new JButton("Limpiar");
    btLimpiar.setBounds(95, 530, 100, 30);
    btLimpiar.setBackground(Color.CYAN);
    btLimpiar.setForeground(Color.black);
    add(btLimpiar);

    btOk = new JButton("Aceptar");
    btOk.setBounds(40, ybt+(11 * yGap), 100, 30);
    btOk.setBackground(Color.LIGHT_GRAY);
    btOk.setForeground(Color.black);
    add(btOk);    

    btCancel = new JButton("Cancelar");
    btCancel.setBounds(160, ybt+(11 * yGap), 100, 30);
    btCancel.setBackground(Color.red);
    btCancel.setForeground(Color.white);
    add(btCancel);


    /* Inicializar datos */
    // FormAdd.desahabilitarTXT();
    txt_envio.setEditable(false);
    txt_pvp.setEditable(false);


    /* Inicializo escuchador del botón */
    btOk.addActionListener(this);
    btCancel.addActionListener(this);
    btLimpiar.addActionListener(this);
    

    sql = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
    //System.out.println("Trabajando en Interfaz Gráfica ....");
    rs = sql.executeQuery("select * from instrumentos");
 
  }

//////////////////////////////////////////////////////////////////////////////////
  /* Método que implementa la acción del botón */


  public void actionPerformed(ActionEvent e) {

    if (e.getSource() == btOk) {
        if(FormAdd.crearItem()==true){
          FormAdd.funcionLimpiar();
          rs = null;
          JOptionPane.showMessageDialog(null, "Item creado correctamente\n", "Información", 1);
          // Formulario.mostrarFormTabla();
          // dispose();
        }        
    }
    
    if (e.getSource() == btLimpiar) {
      FormAdd.funcionLimpiar();
      rs=null;
   }

    if (e.getSource() == btCancel) {
      FormAdd.funcionCancelar();
      dispose();
      Formulario.mostrarFormTabla();
    }

  }
  

  public static void funcionCancelar(){
    btOk.setVisible(false);
    FormAdd.limpiarTXT();
    btCancel.setVisible(false);
    rs=null;
  }

  public static void funcionLimpiar(){
    FormAdd.limpiarTXT();
  }

  public static void limpiarTXT(){
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





