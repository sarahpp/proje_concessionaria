/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import java.sql.*;

/**
 *
 * @author Notebook
 */
public class ModuloConexao {

    public static Connection conector() {

        java.sql.Connection conexao = null;

        String driver = "com.mysql.jdbc.Driver";

        String url = "jdbc:mysql://127.0.0.1:3333/dbproj";
        String user = "root";
        String password = "admin";
        
        try {
            Class.forName(driver);
            conexao = DriverManager.getConnection(url, user, password);
            return conexao;
            
        } catch (Exception e) {
            //a linha abaixo mostra o erro
            //System.out.println(e);
            return null;
        }
    }

}
