package conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {


    public String driver = "com.mysql.cj.jdbc.Driver";
    public String driver2 = "org.postgresql.Driver";

    public String database = "productos";
    // public String database = "garbikot_productos";

    public String hostname = "localhost";
    // public String hostname = "176.31.46.161";

    public String port = "3306";
    public String port2 = "5432";
    // public String port = "2083";

    // Ruta de nuestra base de datos (1 PARA MYSQL Y 2 PARA POSTGRE)
    public String url = "jdbc:mysql://" + hostname + ":" + port + "/" + database + "?useSSL=false";
    //public String url ="jdbc:postgresql://" + hostname + ":" + port2 + "/" + database ;

    public String username = "root";
    public String username2 = "postgres";
    // public String username = "garbikot_productos";

    public String password = "";
    public String password2 = "1234";
    // public String password = "Paul123456";

    public Connection conectarMySQL() throws ClassNotFoundException{
        Connection conexion = null;

        try {
            Class.forName(driver2);
            conexion = DriverManager.getConnection(url, username, password);
            //  conexion = DriverManager.getConnection(url, username2, password2);

            if (url.substring(5,10).equals("mysql")){
                System.out.println("Conexión establecida con MYSQL");
            } else {
                System.out.println("Conexión establecida con POSTGRESQL");
            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return conexion;

    }

}