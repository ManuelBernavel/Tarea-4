
package MSQL;

import java.sql.*;



public class ConexionBD {
    public static String url = "jdbc:mysql://localhost/usuarios";
    public static String usuario = "root";
    public static String contraseña = "12345";
    public static String clase = "com.mysql.cj.jdbc.Driver";
    
    public static Connection conectar(){
        Connection conexion = null;
        
        try {
            Class.forName(clase);
            conexion = DriverManager.getConnection(url, usuario, contraseña);
            System.out.println("conexion exitosa");
        } catch(ClassNotFoundException | SQLException e){
            System.out.println(e);
        }
        return conexion;
    }
}
