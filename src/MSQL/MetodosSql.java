
package MSQL;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import tareafinal.Usuarios;


public class MetodosSql extends ConexionBD{
    
    
    
    public static ResultSet resultado;
    public static String sql;
    public static int intResultado = 0;
    
    public boolean guardar(Usuarios usr){
        
        PreparedStatement ps = null;
        Connection conexion = conectar();
        
        String sql = ("INSERT INTO lista_usuarios(nombre_usuario, nombres, apellidos, telefono, email, contraseña ) VALUES (?,?,?,?,?,?)");
        
        try{
            ps = conexion.prepareStatement(sql);
           
            ps.setString(1, usr.getNombre_usuario());
            ps.setString(2, usr.getNombres());
            ps.setString(3, usr.getApellidos());
            ps.setString(4, usr.getTelefono());
            ps.setString(5, usr.getEmail());
            ps.setString(6, usr.getContraseña());
            ps.execute();
            ps.close();
            return true;
            
            
        } catch(SQLException e){
            System.out.println(e);
        }
        return false;
    }
    public boolean login(Usuarios usr){
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection conexion = null;
        
        
        
        String sql = "SELECT id, nombre_usuario, nombres, contraseña FROM lista_usuarios WHERE nombre_usuario = ?" ;
        
        try{
            conexion = ConexionBD.conectar();
            ps = conexion.prepareStatement(sql);
            ps.setString(1, usr.getNombre_usuario());
            rs = ps.executeQuery();
            
            if(rs.next()){
                if(usr.getContraseña().equals(rs.getString(4))){
                    usr.setId(rs.getInt(1));
                    usr.setNombres(rs.getString(3));
                    return true;
                }else{
                    return false;
                }
            
            } 
            return false;
        } catch(SQLException e){
            System.out.println(e);
            return false;
        }
        
    }
    public int existeUsuario(String nombre_usuario){
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection conexion = conectar();
        
        
        
        String sql = "SELECT count(id) FROM lista_usuarios WHERE nombre_usuario = ?" ;
        
        try{
            ps = conexion.prepareStatement(sql);
            ps.setString(1, nombre_usuario);
            rs = ps.executeQuery();
            
            if(rs.next()){
                return rs.getInt(1);
            } 
            return 1;
        } catch(SQLException e){
            System.out.println(e);
            return 1;
        }
        
    }
    public List listar(){
        ConexionBD conexion = new ConexionBD();
        Connection con;
        PreparedStatement ps;
        ResultSet rs;
        
        List<Usuarios>datos = new ArrayList<>();
        String sql = "SELECT * FROM lista_usuarios";
        
        try{
            
            con = conexion.conectar();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                Usuarios p = new Usuarios();
                p.setId(rs.getInt(1));
                p.setNombres(rs.getNString(3));
                p.setApellidos(rs.getString(4));
                p.setTelefono(rs.getString(5));
                p.setEmail(rs.getString(6));
                datos.add(p);
            }
            
            
        } catch(Exception e){
        
          }
        
        return datos;
    
    }
    public void delete(int id){
        
        ConexionBD conexion = new ConexionBD();
        Connection con;
        PreparedStatement ps;
        ResultSet rs;
    
        String sql = "DELETE FROM lista_usuarios WHERE id=" + id;
        
        try{
            
            con = conexion.conectar();
            ps = con.prepareStatement(sql);
            ps.execute();
        } catch (Exception e){
        
         }
        
    
    }
    public int Actualizar(Usuarios p){
        ConexionBD conexion = new ConexionBD();
        Connection con;
        PreparedStatement ps;
        ResultSet rs;
        int r=0;
        String sql = "update lista_usuarios set nombres=?, apellidos=?, telefono=?, email=? where id=?";
        
        try{
            
            con = conexion.conectar();
            ps = con.prepareStatement(sql);
            ps.setString(1,p.getNombres());
            ps.setString(2,p.getApellidos());
            ps.setString(3,p.getTelefono());
            ps.setString(4,p.getEmail());
            ps.setInt(5, p.getId());
            
            r = ps.executeUpdate();
            if(r==1){
                return 1;
            }else{
                return 0;
            }
            
            
        } catch(Exception e){
        
        }
        
        return r;
    }
}   
