/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package library;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author JP
 */
public class Library {

    Connection conexion = null;

    public Connection Conectar(String URL, String usuario, String contraseña) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conexion = (Connection) DriverManager.getConnection(URL, usuario, contraseña);
            JOptionPane.showMessageDialog(null, "Se ha establecido conexion");
        } catch (ClassNotFoundException | SQLException e) {
            JOptionPane.showMessageDialog(null, "No se ha establecido conexion");
        }
        return conexion;
    }

    public void Insertar(String Tabla, String parametros) {

        try {
            PreparedStatement st = conexion.prepareStatement("insert into " + Tabla + " VALUES(" + parametros + ")");
            st.executeUpdate();

        } catch (SQLException ex) {
            System.out.println("Error en la insercion");
        }
    }

    public void BorrarDatos(String Tabla, int ID) {

        try {
            PreparedStatement st = conexion.prepareStatement("delete from " + Tabla + " where id=" + ID);
            st.executeUpdate();

        } catch (SQLException ex) {
            System.out.println("Error en al borrar");
        }

    }
    
    public String consultaDatos(String Tabla,int numcolumnas, String datosVisualizar){
        
        String visualizar="";
        try {
            Statement prepstat= conexion.createStatement();
            ResultSet rs= prepstat.executeQuery("select "+datosVisualizar+" from "+Tabla);
            String [] datos= new String[numcolumnas-1];
            while(rs.next()){
          
                for (int i = 0; i < datos.length; i++) {
                    datos[i]=rs.getString(i+1);
                    visualizar= visualizar +" "+datos[i];
                
                }
            }
                   
        } catch (SQLException ex) {
            System.out.println("Error al viosualizar");
        }
        return visualizar;
}

}
