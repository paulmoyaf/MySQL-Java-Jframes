package formularios;


import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.Node;

import querys.LecturaBBDD;
import tabla.Render;
import productos.Teclado;

public class FormTabla extends JFrame implements ActionListener {

    JPanel contentPane = null;
    JTable tabla = null;
    static JTable tablaSearch = null;
    DefaultTableModel modelo = null;
    JScrollPane desplazamiento = null;
    private javax.swing.JButton btExportXML;
    JButton btAdd, btBorrar, btCambiar, btCambiar2, btMenu, btSearch, btClean, btExport;
    ImageIcon imageCambiar = new ImageIcon("icono/cambiar1.png");
    ImageIcon imageBorrar = new ImageIcon("icono/eliminar1.png");
    ImageIcon imageFind = new ImageIcon("icono/find1.png");
    JLabel lb_codeTemp;    
    JTextField txt_titulo, txt_search;
    JLabel lb_Search;
    JComboBox<String> combo;
    static FormAdd formAdd;
    static boolean boolPersonalizada=false;
    String pathCSV = "files/exportCSV.csv";
    String pathToExportToXML = "files/exportXML.xml";

    public FormTabla() throws Exception {
        String[] columnas = {"Código", "Marca", "Precio", "Descuento", "Tipo", "Color","Teclas","Conector","Envío","PVP","Modificar","Eliminar"};
        String[] lista = {"codigo", "marca", "precio", "descuento", "tipo", "color","teclas","conector","envio"};
        
        contentPane = new JPanel();

        int widthTotal = 1065;
        int heightTotal = 800;
        
        setBounds(0, 0, widthTotal , heightTotal);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        
        tabla = new JTable();
        modelo = new DefaultTableModel();
        desplazamiento = new JScrollPane(tabla);
        
        // Parametros de la ventana
        this.setTitle("Stock de Productos");
        this.setLocationRelativeTo(null);
        
        // Modelo de la tabla
        contentPane.add(desplazamiento);
        modelo.setColumnIdentifiers(columnas);
        
        //tamano de la tabla
        desplazamiento.setBounds(25, 120, 1000, 570);
        desplazamiento.setViewportView(tabla);

        // Barras de desplazamiento
        desplazamiento.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        desplazamiento.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        // Propiedades de la tabla
        tabla.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        tabla.setFillsViewportHeight(true);
// 
        // tabla.setModel(modelo);
        tabla.setRowHeight(50);

   
        // Agregamos datos
        this.llenarJTabla(tabla);
        
        
        int ybt = 50;
        int yGap = 50;

        btAdd = new JButton("Crear");
        btAdd.setBounds(25, 75, 100, 30);
        btAdd.setBackground(Color.ORANGE);
        btAdd.setForeground(Color.black);
        add(btAdd);

        lb_Search = new JLabel("Buscar por:");
        lb_Search.setBounds(520, 75, 100, 30);
        // lb_Search.setHorizontalAlignment(SwingConstants.RIGHT);
        add(lb_Search);

        combo = new JComboBox<String>(lista);
        combo.setBounds(600, 75, 100, 30);
        add(combo);
 
        txt_search = new JTextField();
        txt_search.setBounds(720, 75, 80, 30);
        add(txt_search);

        btSearch = new JButton();
        btSearch.setBounds(820, 75, 35, 30);
        btSearch.setIcon(imageFind);
        // btSearch.setBackground(Color.BLUE);
        btSearch.setForeground(Color.black);
        add(btSearch);

        btClean = new JButton("Limpiar");
        btClean.setBounds(940, 75, 80, 30);
        btClean.setBackground(Color.LIGHT_GRAY);
        btClean.setForeground(Color.black);
        add(btClean);

        lb_codeTemp = new JLabel("temp");
        lb_codeTemp.setBounds(20, 715, 100, 30);
        lb_codeTemp.setVisible(false);
        add(lb_codeTemp);

        JTextField txt_titulo = new JTextField();
        txt_titulo.setBounds(0, 0, widthTotal, 60);
        txt_titulo.setBackground(Color.ORANGE);
        txt_titulo.setText("PAUL'S MUSIC STORE STOCK");
        txt_titulo.setFont(new Font("MONOSPACED",1,16));
        txt_titulo.setHorizontalAlignment(0);
        txt_titulo.setEditable(false);
        add(txt_titulo);


        btMenu = new JButton("MENU");
        btMenu.setBounds(920, 705, 100, 30);
        btMenu.setBackground(Color.black);
        btMenu.setForeground(Color.white);
        add(btMenu);

        btExport = new JButton("EXPORTAR A CSV");
        btExport.setBounds(25, 705, 150, 30);
        btExport.setBackground(Color.getHSBColor(80, 100, 200));
        btExport.setForeground(Color.black);
        add(btExport);

        btExportXML = new javax.swing.JButton();
        btExportXML.setText("EXPORTAR A XML");
        btExportXML.setBounds(200, 705, 150, 30);
        btExportXML.setBackground(Color.getHSBColor(80, 100, 200));
        btExportXML.setForeground(Color.black);
        add(btExportXML);

        btAdd.addActionListener(this);
        btMenu.addActionListener(this);
        btSearch.addActionListener(this);
        btClean.addActionListener(this);
        btExport.addActionListener(this);
        btExportXML.addActionListener(this);
        // btExportXML.addActionListener(new java.awt.event.ActionListener() {
        //     public void actionPerformed(java.awt.event.ActionEvent evt) {
        //         btExportXMLActionPerformed(evt);
        //     }
        // });



        tabla.addMouseListener(new java.awt.event.MouseAdapter() {

            public void mouseClicked(java.awt.event.MouseEvent evt) {
                String codeTemp = "";
                lb_codeTemp.setText(tempMouseClicked(evt));
                codeTemp = lb_codeTemp.getText();
                try {
                    tablaMouseClicked(evt, codeTemp);
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        });

    
    }
    //private void btExportXMLActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        //llamamos al metodo para crear el xml
        //CrearXml(tabla);
   // }//GEN-LAST:event_jButton2ActionPerformed

    // @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == btAdd) {
            // FormAdd form;
            try {
                formAdd = new FormAdd();
                formAdd.setVisible(true);
                dispose();
            } catch (Exception e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        }

        if (e.getSource() == btMenu) {
            Formulario form;
            try {
                form = new Formulario();
                form.setVisible(true);
                dispose();
            } catch (Exception e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        }

        if (e.getSource() == btSearch) {
            try {
                limpiarJTabla(tabla);
                tablaSearch = new JTable();
                llenarJTablaPersonalizada(tablaSearch,String.valueOf(combo.getSelectedItem()),txt_search.getText());
                boolPersonalizada=true; 
            } catch (Exception e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        }

        if (e.getSource() == btExport) {
            try {
                exportToCSV(tabla, pathCSV);
                JOptionPane.showMessageDialog(null, "Archivo exportado correctamente en: "+ pathCSV, "Exportar a CSV", 1);
            } catch (Exception e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        }

        if (e.getSource() == btExportXML) {
            try {
                // CrearXml(tabla, "exportXML");
                CrearXml(tabla, pathToExportToXML);
                JOptionPane.showMessageDialog(null, "Archivo exportado correctamente en: "+ pathToExportToXML, "Exportar a XML", 1);
            } catch (Exception e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        }

        if (e.getSource() == btClean) {
            try {
                limpiarJTabla(tabla);
                llenarJTabla(tabla);
                txt_search.setText("");
            } catch (Exception e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        }
    }

    public static void setColumnWidths(JTable table, int... widths) {
        TableColumnModel columnModel = table.getColumnModel();
        for (int i = 0; i < widths.length; i++) {
            if (i < columnModel.getColumnCount()) {
                columnModel.getColumn(i).setMaxWidth(widths[i]);
            }
            else break;
        }
    }

    private Teclado obtenerObj(int row) throws Exception {
        Teclado teclado = new Teclado();

        try {
            String codigo   = tabla.getValueAt(row, 0).toString();
            String marca    = tabla.getValueAt(row, 1).toString();
            String precio   = tabla.getValueAt(row, 2).toString();
            String dcto     = tabla.getValueAt(row, 3).toString();
            String tipo     = tabla.getValueAt(row, 4).toString();
            boolean prime   = false;
            if (tipo.equalsIgnoreCase("PRIME")) {
                prime = true;
            }
            String color    = tabla.getValueAt(row, 5).toString();
            String teclas   = tabla.getValueAt(row, 6).toString();
            String conector = tabla.getValueAt(row, 7).toString();
            String envio    = tabla.getValueAt(row, 8).toString();
            String pvp      = tabla.getValueAt(row, 9).toString();

            teclado.setId(codigo);
            teclado.setMarca(marca);
            teclado.setPrecio(Double.parseDouble(precio));
            // teclado.setPrecio(precio);
            teclado.setDcto(Double.parseDouble(dcto));
            // teclado.setDcto(dcto);
            teclado.setPrime(prime);
            teclado.setColor(color);
            teclado.setTeclas(Integer.parseInt(teclas));
            teclado.setConector(conector);

            // System.out.print(teclado.getId(),teclado.getMarca(),teclado.getPrecio(),teclado.getDcto(),teclado.isPrime(),teclado.getColor(),teclado.getTeclas(),teclado.getConector());
            teclado.mostrarInfo();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Item in table was not a valid double - "
                    + e.getMessage());

        } catch (Exception e) {
            // TODO: handle exception
        }
        return teclado;

    }
  
    private String tempMouseClicked(java.awt.event.MouseEvent evt){
        int column = tabla.getColumnModel().getColumnIndexAtX(evt.getX());
        int row = evt.getY()/tabla.getRowHeight();
        String codeTemp = "";

        if(row < tabla.getRowCount() && row >= 0 && column < tabla.getColumnCount() && column >= 0){
            // Object value = tabla.getValueAt(row, column);
            codeTemp = (String) tabla.getValueAt(row, 0);
        }
        return codeTemp;
    }

    private void tablaMouseClicked(java.awt.event.MouseEvent evt, String codeTemp) throws Exception{
        // tabla.setEnabled(true);
        
        int column = tabla.getColumnModel().getColumnIndexAtX(evt.getX());
        int row = evt.getY()/tabla.getRowHeight();
        
        Teclado teclado = new Teclado();

        if(row < tabla.getRowCount() && row >= 0 && column < tabla.getColumnCount() && column >= 0){
            Object value = tabla.getValueAt(row, column);
           
            if(value instanceof JButton){
                ((JButton)value).doClick();
                JButton boton = (JButton) value;
                if(boton.getName().equals("c")){
                    //EVENTOS MODIFICAR
                    // JOptionPane.showConfirmDialog(null, "Desea Cambiar el Registro?", "Confirmar", JOptionPane.OK_CANCEL_OPTION);
                    try {
                        if (boolPersonalizada==true){
                            teclado = obtenerObj(row);
                            LecturaBBDD.cambiarItemTablaPersonalizado(row, String.valueOf(combo.getSelectedItem()),txt_search.getText(),teclado.getId(),teclado.getMarca(),teclado.getPrecio(),teclado.getDcto(),teclado.isPrime(),teclado.getColor(),teclado.getTeclas(),teclado.getConector());
                            // System.out.println("cambiado");
                            // JOptionPane.showMessageDialog(null, "Item modificado correctamente", "Modificación de Item", 1);
                            limpiarJTabla(tabla);
                            llenarJTablaPersonalizada(tablaSearch,String.valueOf(combo.getSelectedItem()),txt_search.getText());
                            // llenarJTabla(tabla);
                            boolPersonalizada = false;  
                        } else {
                        teclado = obtenerObj(row);
                        LecturaBBDD.cambiarItemTabla(row, teclado.getId(),teclado.getMarca(),teclado.getPrecio(),teclado.getDcto(),teclado.isPrime(),teclado.getColor(),teclado.getTeclas(),teclado.getConector());
                        // System.out.println("cambiado");
                        // JOptionPane.showMessageDialog(null, "Item modificado correctamente", "Modificación de Item", 1);
                        limpiarJTabla(tabla);
                        llenarJTabla(tabla);
                        }    
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(null, "Error" + e, "Error", 0);
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    
                }
                if(boton.getName().equals("b")){
                    //EVENTOS ELIMINAR
                    int in = JOptionPane.showConfirmDialog(null, "Desea eliminar este registro", "Confirmar", JOptionPane.OK_CANCEL_OPTION);
                    if (in == 0) {
                    LecturaBBDD.borrarItem(codeTemp);
                    JOptionPane.showMessageDialog(null, "Item eliminado", "Eliminación de Item", 1);
                    limpiarJTabla(tabla);
                    llenarJTabla(tabla);
                    }

                }
            }
            if(value instanceof JCheckBox){
                //((JCheckBox)value).doClick();
                JCheckBox ch = (JCheckBox)value;
                if(ch.isSelected()==true){
                    ch.setSelected(false);
                }
                if(ch.isSelected()==false){
                    ch.setSelected(true);
                }
            }
        }
        
    }

    public void limpiarJTabla(JTable jTabla){
        while(jTabla.getRowCount()>0)
        {
            modelo.removeRow(0);
        }
    }

    public void llenarJTabla(JTable jtTeclados)throws Exception{

        try{
            // para los botones
            jtTeclados.setDefaultRenderer(Object.class, new Render());
            btCambiar = new JButton();
            btCambiar.setName("c");
            btCambiar.setIcon(imageCambiar);
            btBorrar = new JButton();
            btBorrar.setName("b");
            btBorrar.setIcon(imageBorrar);

            //Se crea un array para llenar las columnas de la tabla
            ArrayList<Object> nombreColumna = new ArrayList<>();
            nombreColumna.removeAll(nombreColumna);
            nombreColumna.add("id");
            nombreColumna.add("marca");
            nombreColumna.add("precio");
            nombreColumna.add("descuento");
            nombreColumna.add("tipo");
            nombreColumna.add("color");
            nombreColumna.add("teclas");
            nombreColumna.add("conector");
            nombreColumna.add("envio");
            nombreColumna.add("pvp");

            //se rellena con cada una de las columnas al array

            for(Teclado teclado : LecturaBBDD.listar()){
                modelo.addRow(new Object[]{	teclado.getId(),
											teclado.getMarca(),
											teclado.getPrecio(),
											teclado.getDcto(),
											teclado.isPrime(),
											teclado.getColor(),
											teclado.getTeclas(),
											teclado.getConector(),
											teclado.getEnvio(),
											teclado.getPrecioVenta(),btCambiar,btBorrar}); 
            }
            //se actualiza la Tabla
            jtTeclados.setModel(modelo);
        //en caso de error
        }catch(Exception sqle){
            JOptionPane.showMessageDialog(null,"Error llenar JTable " + sqle);
        }
    }
    
    public void llenarJTablaPersonalizada(JTable jtTeclados, String var, String txt_code)throws Exception{

        try{
            // para los botones
            jtTeclados.setDefaultRenderer(Object.class, new Render());
            btCambiar2 = new JButton();
            btCambiar2.setName("c");
            btCambiar2.setIcon(imageCambiar);
            btBorrar = new JButton();
            btBorrar.setName("b");
            btBorrar.setIcon(imageBorrar);

            //Se crea un array para llenar las columnas de la tabla
            ArrayList<Object> nombreColumna = new ArrayList<>();
            nombreColumna.removeAll(nombreColumna);
            nombreColumna.add("id");
            nombreColumna.add("marca");
            nombreColumna.add("precio");
            nombreColumna.add("descuento");
            nombreColumna.add("tipo");
            nombreColumna.add("color");
            nombreColumna.add("teclas");
            nombreColumna.add("conector");
            nombreColumna.add("envio");
            nombreColumna.add("pvp");

            //se rellena con cada una de las columnas al array

            for(Teclado teclado : LecturaBBDD.buscarItemPersonalizado(var,txt_code)){
                modelo.addRow(new Object[]{	teclado.getId(),
											teclado.getMarca(),
											teclado.getPrecio(),
											teclado.getDcto(),
											teclado.isPrime(),
											teclado.getColor(),
											teclado.getTeclas(),
											teclado.getConector(),
											teclado.getEnvio(),
											teclado.getPrecioVenta(),btCambiar2,btBorrar}); 
            }
            //se actualiza la Tabla
            jtTeclados.setModel(modelo);
        //en caso de error
        }catch(Exception sqle){
            JOptionPane.showMessageDialog(null,"Error llenar JTable " + sqle);
        }
    }
    
    public static boolean exportToCSV(JTable tableToExport,String pathToExportTo) {
        try {
            TableModel model = tableToExport.getModel();
            FileWriter csv = new FileWriter(new File(pathToExportTo));
            // for (int i = 0; i < model.getColumnCount(); i++) {
            for (int i = 0; i < 10; i++) {
                if (i == 9) {
                    csv.write(model.getColumnName(i));
                } else {
                    csv.write(model.getColumnName(i) + ",");
                }
            }

            csv.write("\n");

            for (int i = 0; i < model.getRowCount(); i++) {
                // for (int j = 0; j < model.getColumnCount(); j++) {
                for (int j = 0; j < 10; j++) {
                    if (j == 9) {
                        csv.write(model.getValueAt(i, j).toString());
                        csv.write("\n");
                    } else {
                        csv.write(model.getValueAt(i, j).toString() + ",");
                    }
                }
            }

            csv.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    // protected DocumentBuilder docFactory = null;
    // protected DocumentBuilder docBuilder = null;

    public void CrearXml(JTable tb, String pathToExportToXML){
        try{
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            
            org.w3c.dom.Document doc = docBuilder.newDocument();
            org.w3c.dom.Element rootElement = doc.createElement("productos");
            doc.appendChild(rootElement);
            
            int i=0;
            
            while (i<tb.getRowCount()){
                int j=0;
                org.w3c.dom.Element rows = doc.createElement("teclado");
                rootElement.appendChild(rows);
                
                Attr attr = doc.createAttribute("No.");
                attr.setValue((i+1)+"");
                rows.setAttributeNode(attr);
                
                while (j<tb.getColumnCount()-2){
                    org.w3c.dom.Element element = doc.createElement(tb.getTableHeader().getColumnModel().getColumn(j).getHeaderValue()+"");
                    element.appendChild(doc.createTextNode(tb.getModel().getValueAt(i,j)+""));
                    rows.appendChild(element);
                    j++;
                }
                i++;
            }
            
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            
            DOMSource source = new DOMSource((Node) doc);
            StreamResult result;
            
            try{
                FileOutputStream fileOutputStream = null;
                
                fileOutputStream = new FileOutputStream(new File(pathToExportToXML));
                
                result = new StreamResult(fileOutputStream);
                transformer.setOutputProperty(OutputKeys.INDENT, "yes");
                transformer.transform(source, result);
                try{
                    fileOutputStream.close();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }catch (FileNotFoundException e){
                e.printStackTrace();
            }
            
        }catch (ParserConfigurationException pce){
            pce.printStackTrace();
        } catch (TransformerException te){
            te.printStackTrace();
        }
    }

}