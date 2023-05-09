package querys;

import java.sql.*;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import conexion.Conexion;
import productos.Teclado;

public class LecturaBBDD extends javax.swing.JFrame{
	

	public static ResultSet conexionResultSet(Conexion conexion) throws ClassNotFoundException, SQLException{
		Statement sql = null;
		ResultSet rs = null;
		// Conexion conexion = new Conexion();
		sql = conexion.conectarMySQL().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
		System.out.println("rs Establecida");
		return rs = sql.executeQuery("select * from instrumentos");
	}

	public static void cuteTitulo() {
        System.out.printf("\n+-------+----------+-------+---------+--------+----------+-------+--------+-------+-------+\n");
        System.out.printf("|%-7s|%-10s|%-7s|%-6s|%-8s|%-10s|%-7s|%-8s|%-7s|%-7s|\n","ID","MARCA","PRECIO","DESCUENTO","TIPO","COLOR","TECLAS","CONECTOR","ENVIO","PVP");
        System.out.printf("+-------+----------+-------+---------+--------+----------+-------+--------+-------+-------+\n");
        // System.out.printf("+-------+----------+-------+---------+--------+----------+-------+--------+-------+-------+\n");
    }

	public static ArrayList<Teclado> listar() throws ClassNotFoundException {

		ArrayList<Teclado> arrTeclados = new ArrayList<Teclado>();

		Conexion conexion = new Conexion();
		Statement sql = null;
		ResultSet rs = null;



			try {
				// System.out.println("Conexión establecida");
				sql = conexion.conectarMySQL().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
				rs = sql.executeQuery("select * from instrumentos");

				if(rs.isBeforeFirst()){

				System.out.println("Consulta ejecutada correctamente...");
				cuteTitulo();
				boolean r = rs.next();
				while (r) {
					Teclado teclado = new Teclado();
					String id = rs.getString("codigo");
					String marca = rs.getString("marca");
					Double precio = rs.getDouble("precio");
					Double dcto = rs.getDouble("descuento");
					String tipo = rs.getString("tipo");
					Boolean prime;
					String color = rs.getString("color");
					int teclas = rs.getInt("teclas");
					String conector = rs.getString("conector");
					String envio = rs.getString("envio");
					String pvp = rs.getString("pvp");

					if (tipo.equals("PRIME")) {
						prime = true;
					} else {
						prime = false;
					}

					teclado.setId(id);
					teclado.setMarca(marca);
					teclado.setPrecio(precio);
					teclado.setDcto(dcto);
					teclado.setPrime(prime);
					teclado.setColor(color);
					teclado.setTeclas(teclas);
					teclado.setConector(conector);

					teclado.toStringCute();
					r = rs.next();
					arrTeclados.add(teclado);
				}

				conexion.conectarMySQL().close();
				System.out.println("\nCerrando la conexión");
			}else{
				System.out.println("NO EXISTE NINGUN ELEMENTO EN LA BASE DE DATOS");
			}

			} catch (SQLException e) {
				System.out.println("ERROR AL EJECUTAR LA SENTENCIA SQL");
			}
		return arrTeclados;

	}

	public static ArrayList<Teclado> buscarItemPersonalizado(String var, String txt_code) throws Exception {

		ArrayList<Teclado> arrTeclados = new ArrayList<Teclado>();
		Conexion conexion = new Conexion();
		Statement sql = null;
		ResultSet rs = null;
		// Teclado teclado = new Teclado();
			try {
				sql = conexion.conectarMySQL().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
				// System.out.println("Conexión establecida");
				rs = sql.executeQuery("select * from instrumentos where instrumentos." + var + "=" + "'" +  txt_code + "';");
				cuteTitulo();
				boolean r = rs.next();
				while (r) {
					Teclado teclado = new Teclado();
					String id = rs.getString("codigo");
					String marca = rs.getString("marca");
					Double precio = rs.getDouble("precio");
					Double dcto = rs.getDouble("descuento");
					String tipo = rs.getString("tipo");
					Boolean prime;
					String color = rs.getString("color");
					int teclas = rs.getInt("teclas");
					String conector = rs.getString("conector");
					String envio = rs.getString("envio");
					String pvp = rs.getString("pvp");

					if (tipo.equals("PRIME")) {
						prime = true;
					} else {
						prime = false;
					}

					teclado.setId(id);
					teclado.setMarca(marca);
					teclado.setPrecio(precio);
					teclado.setDcto(dcto);
					teclado.setPrime(prime);
					teclado.setColor(color);
					teclado.setTeclas(teclas);
					teclado.setConector(conector);

					teclado.toStringCute();
					r = rs.next();
					arrTeclados.add(teclado);

				}
				conexion.conectarMySQL().close();
				// conexion.close();
				// System.out.println("\nCerrando la conexión");

				
			} catch (SQLException e) {
				System.out.println("ERROR AL EJECUTAR LA SENTENCIA SQL");
			}

		return arrTeclados;
		// return rs;
	}

	public static Teclado buscarItem(String txt_code) throws Exception {

		Conexion conexion = new Conexion();
		Statement sql = null;
		ResultSet rs = null;
		Teclado teclado = new Teclado();


			try {
				sql = conexion.conectarMySQL().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
				// System.out.println("Conexión establecida");
				rs = sql.executeQuery("select * from instrumentos where instrumentos.codigo = '" + txt_code + "';");
				boolean r = rs.next();
				while (r) {

					String id = rs.getString("codigo");
					String marca = rs.getString("marca");
					Double precio = rs.getDouble("precio");
					Double dcto = rs.getDouble("descuento");
					String tipo = rs.getString("tipo");
					Boolean prime;
					String color = rs.getString("color");
					int teclas = rs.getInt("teclas");
					String conector = rs.getString("conector");
					String envio = rs.getString("envio");
					String pvp = rs.getString("pvp");

					if (tipo.equals("PRIME")) {
						prime = true;
					} else {
						prime = false;
					}

					teclado.setId(id);
					teclado.setMarca(marca);
					teclado.setPrecio(precio);
					teclado.setDcto(dcto);
					teclado.setPrime(prime);
					teclado.setColor(color);
					teclado.setTeclas(teclas);
					teclado.setConector(conector);
					//System.out.println(teclado.toString());
					r = rs.next();

				}

				
			} catch (SQLException e) {
				System.out.println("ERROR AL EJECUTAR LA SENTENCIA SQL");
			}

		return teclado;
	}

	public static void cambiarItemTabla(int row, String txt_id, String txt_marca, Double txt_precio, Double txt_dcto,
	String txt_tipo, String txt_color, Integer txt_teclas, String txt_conector) throws Exception {

		Conexion conexion = new Conexion();
		Statement sql = null;
		ResultSet rs = null;

			try {
				sql = conexion.conectarMySQL().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
				// System.out.println("Conexión establecida");
				rs = sql.executeQuery("select * from instrumentos");
				// rs.moveToCurrentRow();
				rs.absolute(row+1);
				rs.updateString("codigo", txt_id);
				rs.updateString("tipo", txt_tipo);

					String id = rs.getString("codigo");
					String temp = id;
	
					String query = "update instrumentos set codigo = '" + txt_id.toLowerCase() + "', marca = '" + txt_marca.toUpperCase()
					+ "', precio = '" + txt_precio + "', descuento = '" + txt_dcto + "', tipo = '" + txt_tipo.toUpperCase()
					+ "', color = '" + txt_color.toUpperCase() + "', teclas = '" + txt_teclas + "', conector = '" + txt_conector.toUpperCase()
					+ "' where (codigo = '" + temp + "');";
					sql = conexion.conectarMySQL().createStatement();
					sql.executeUpdate(query);

					System.out.println("cambiado");
					JOptionPane.showMessageDialog(null, "Item modificado correctamente", "Modificación de Item", 1);

				
				conexion.conectarMySQL().close();
				// conexion.close();
				System.out.println("\nCerrando la conexión");
				
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null, "Error con la sentencia SQL\n" + e, "Error", 0);
				System.out.println("ERROR AL EJECUTAR LA SENTENCIA SQL");
			}

	}
	
	public static void cambiarItemTablaPersonalizado(int row, String var, String txt_code,String txt_id, String txt_marca, Double txt_precio, Double txt_dcto,
	String txt_tipo, String txt_color, Integer txt_teclas, String txt_conector) throws Exception {

		Conexion conexion = new Conexion();
		Statement sql = null;
		ResultSet rs = null;

			try {
				sql = conexion.conectarMySQL().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
				// System.out.println("Conexión establecida");
				rs = sql.executeQuery("select * from instrumentos where instrumentos." + var + "=" + "'" +  txt_code + "';");
				// rs.moveToCurrentRow();
				rs.absolute(row+1);

					rs.updateString("codigo", txt_id);
					rs.updateString("marca", txt_marca);
					rs.updateDouble("precio", txt_precio);
					rs.updateDouble("descuento", txt_dcto);
					rs.updateString("tipo", txt_tipo);
					rs.updateString("color", txt_color);
					rs.updateInt("teclas", txt_teclas);
					rs.updateString("conector", txt_conector);

					String id = rs.getString("codigo");
					String temp = id;
	
					String query = "update instrumentos set codigo = '" + txt_id.toLowerCase() + "', marca = '" + txt_marca.toUpperCase()
					+ "', precio = '" + txt_precio + "', descuento = '" + txt_dcto + "', tipo = '" + txt_tipo.toUpperCase()
					+ "', color = '" + txt_color.toUpperCase() + "', teclas = '" + txt_teclas + "', conector = '" + txt_conector.toUpperCase()
					+ "' where (codigo = '" + temp + "');";
					sql = conexion.conectarMySQL().createStatement();
					sql.executeUpdate(query);

					System.out.println("cambiado");
					JOptionPane.showMessageDialog(null, "Item modificado correctamente", "Modificación de Item", 1);
				
				conexion.conectarMySQL().close();
				// conexion.close();
				System.out.println("\nCerrando la conexión");
				
			} catch (SQLException e) {
				System.out.println("ERROR AL EJECUTAR LA SENTENCIA SQL");
				JOptionPane.showMessageDialog(null, "Error con la sentencia SQL\n" + e, "Error", 0);
			}

	}

	public static boolean cambiarItem(String txt_code, String txt_id, String txt_marca, Double txt_precio, Double txt_dcto,
			String combo, String txt_color, Integer txt_teclas, String txt_conector) throws Exception {

		Conexion conexion = new Conexion();
		Statement sql = null;
		boolean bool = false;
		// ResultSet rs = null;

		try {
			// System.out.println("Conexión establecida");
			String query = "update instrumentos set codigo = '" + txt_id.toLowerCase() + "', marca = '"
					+ txt_marca.toUpperCase()
					+ "', precio = '" + txt_precio + "', descuento = '" + txt_dcto + "', tipo = '" + combo.toUpperCase()
					+ "', color = '" + txt_color.toUpperCase() + "', teclas = '" + txt_teclas + "', conector = '"
					+ txt_conector.toUpperCase()
					+ "' where (codigo = '" + txt_code + "');";
			// sql = conexion.createStatement();
			sql = conexion.conectarMySQL().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
			sql.executeUpdate(query);

			System.out.println("\nCambios realizados satisfactoriamente....");
			conexion.conectarMySQL().close();
			System.out.println("\nCerrando la conexión");
			bool = true;

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Error con los datos ingresados\n" + e, "Error", 0);
			System.out.println("ERROR AL EJECUTAR LA SENTENCIA SQL" + e);
		}
		return bool;
	}

	public static Boolean crearItem(String txt_id, String txt_marca, Double txt_precio, Double txt_dcto, String combo,
			String txt_color, Integer txt_teclas, String txt_conector) throws Exception {

		Conexion conexion = new Conexion();
		Statement sql = null;
		boolean bool = false;

		try {
			// System.out.println("Conexión establecida");
			String query = "insert into instrumentos (codigo , marca , precio , descuento , tipo, color, teclas, conector) values ('"
					+ txt_id.toLowerCase() + "','" + txt_marca.toUpperCase() + "','" + txt_precio + "','" + txt_dcto
					+ "','" + combo + "','"
					+ txt_color.toUpperCase() + "','" + txt_teclas + "','" + txt_conector.toUpperCase() +
					"');";
			sql = conexion.conectarMySQL().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_UPDATABLE);

			sql.executeUpdate(query);

			System.out.println("\nInstrumento creado satisfactoriamente....");
			conexion.conectarMySQL().close();
			// System.out.println("\nCerrando la conexión");
			bool = true;

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Error con los datos ingresados\n" + e, "Error", 0);
			System.out.println("ERROR AL EJECUTAR LA SENTENCIA SQL" + e);
		}
		return bool;
	}

	public static void borrarItem(String txt_code) throws Exception {

		Conexion conexion = new Conexion();
		Statement sql = null;

			try {
	
				String query = "delete from instrumentos where (codigo = '" + txt_code + "');";
				sql = conexion.conectarMySQL().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
				sql.executeUpdate(query);

				System.out.printf("\nItem borrado %s satisfactoriamente....", txt_code);
				conexion.conectarMySQL().close();
				// System.out.println("\nCerrando la conexión");

			} catch (SQLException e) {
				System.out.println("ERROR AL EJECUTAR LA SENTENCIA SQL" + e);
			}
	}

	public static Teclado previousItem(ResultSet rs) throws Exception {
		Teclado teclado = new Teclado();
		try {
			if (rs.isFirst() == false) {
				rs.previous();

				String id = rs.getString("codigo");
				String marca = rs.getString("marca");
				Double precio = rs.getDouble("precio");
				Double dcto = rs.getDouble("descuento");
				String tipo = rs.getString("tipo");
				Boolean prime;
				String color = rs.getString("color");
				int teclas = rs.getInt("teclas");
				String conector = rs.getString("conector");
				String envio = rs.getString("envio");
				String pvp = rs.getString("pvp");

				if (tipo.equals("PRIME")) {
					prime = true;
				} else {
					prime = false;
				}

				teclado.setId(id);
				teclado.setMarca(marca);
				teclado.setPrecio(precio);
				teclado.setDcto(dcto);
				teclado.setPrime(prime);
				teclado.setColor(color);
				teclado.setTeclas(teclas);
				teclado.setConector(conector);
				System.out.println(teclado.toString());
			} else {
				return teclado = null;
			}

		} catch (SQLException e) {
			System.out.println("ERROR AL EJECUTAR LA SENTENCIA SQL");
		}
		return teclado;
	}

	public static Teclado nextItem(ResultSet rs) throws Exception {
		Teclado teclado = new Teclado();

		try {
			if (rs.isLast() == false) {
				rs.next();
				// rs.previous();

				String id = rs.getString("codigo");
				String marca = rs.getString("marca");
				Double precio = rs.getDouble("precio");
				Double dcto = rs.getDouble("descuento");
				String tipo = rs.getString("tipo");
				Boolean prime;
				String color = rs.getString("color");
				int teclas = rs.getInt("teclas");
				String conector = rs.getString("conector");
				String envio = rs.getString("envio");
				String pvp = rs.getString("pvp");

				if (tipo.equals("PRIME")) {
					prime = true;
				} else {
					prime = false;
				}

				teclado.setId(id);
				teclado.setMarca(marca);
				teclado.setPrecio(precio);
				teclado.setDcto(dcto);
				teclado.setPrime(prime);
				teclado.setColor(color);
				teclado.setTeclas(teclas);
				teclado.setConector(conector);

				System.out.println(teclado.toString());
			} else {
				return teclado = null;
			}

		} catch (SQLException e) {
			System.out.println("ERROR AL EJECUTAR LA SENTENCIA SQL");
		}
		return teclado;
	}

	public static Teclado primerItem() throws Exception {
		Teclado teclado = new Teclado();

		try{
			Conexion conexion = new Conexion();
			conexion.conectarMySQL();
			Statement sql = null;
			ResultSet rs = null;
			
			sql = conexion.conectarMySQL().createStatement();
			System.out.println("Conexión establecida");
			rs = sql.executeQuery("select * from instrumentos");
			
			boolean r = rs.next();

			while (r) {

				String id = rs.getString("codigo");
				String marca = rs.getString("marca");
				Double precio = rs.getDouble("precio");
				Double dcto = rs.getDouble("descuento");
				String tipo = rs.getString("tipo");
				Boolean prime;
				String color = rs.getString("color");
				int teclas = rs.getInt("teclas");
				String conector = rs.getString("conector");
				String envio = rs.getString("envio");
				String pvp = rs.getString("pvp");

				if (tipo.equals("PRIME")) {
					prime = true;
				} else {
					prime = false;
				}

				teclado.setId(id);
				teclado.setMarca(marca);
				teclado.setPrecio(precio);
				teclado.setDcto(dcto);
				teclado.setPrime(prime);
				teclado.setColor(color);
				teclado.setTeclas(teclas);
				teclado.setConector(conector);

				System.out.println(teclado.toString());
				r = false;
				// rs.next();
			}
		// }
			conexion.conectarMySQL().close();
			System.out.println("\nCerrando la conexión");

		} catch (SQLException e) {
			System.out.println("ERROR AL EJECUTAR LA SENTENCIA SQL");
		}
		return teclado;
	}

}

