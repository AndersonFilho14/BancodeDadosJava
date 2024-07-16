/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;
import Model.Usuario;
import com.mysql.cj.jdbc.result.ResultSetFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


/**
 *
 * @author filho
 */
public class UsuarioDAO {

        private final Connection connection;

    public UsuarioDAO(Connection connection) {
        this.connection = connection;
    }
        public void insert(Usuario usuario) throws SQLException{
            
            String sql = "insert into usuario(usuario,senha) values ('"+usuario.getUsuario()+"','"+usuario.getSenha()+"'); ";
            connection.prepareStatement(sql).execute();
             
        }
        
        public boolean existeNoBancoPorUsuarioESenha(Usuario usuario) throws SQLException {
        String sql = "Select * from usuario where usuario  = '"+usuario.getUsuario()+"' and senha = '"+usuario.getSenha()+"'";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.execute();
            
            statement.getResultSet();
            
            return statement.getResultSet().next();
    }
        
}
