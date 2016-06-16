/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package library;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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

}
