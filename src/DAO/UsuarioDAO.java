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
import java.util.ArrayList;


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
            
            String sql = "Select * from usuario where usuario  = ? and senha = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, usuario.getUsuario());
            statement.setString(2, usuario.getSenha());
            statement.execute();
        }
        
        public void update(Usuario usuario) throws SQLException{
            String sql = "update usuario set usuario  = ?, senha = ? where id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, usuario.getUsuario());
            statement.setString(2, usuario.getSenha());
            statement.setInt(3, usuario.getId());
        }
        public void insertOrUpdate(Usuario usuario) throws SQLException{
            if(usuario.getId() > 0){
                update(usuario);
        }else{
                insert(usuario);
             }
        }
        
        public void delete(Usuario usuario) throws SQLException{
            String sql = "delete from usuario where id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, usuario.getId());
            statement.execute();
        }
        
        public ArrayList<Usuario> selectAll() throws SQLException{
            String sql = "select * from usuario";
            PreparedStatement statement = connection.prepareCall(sql);
            
            return pesquisa(statement);
        }

    private ArrayList<Usuario> pesquisa(PreparedStatement statement) throws SQLException {
        ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
        statement.execute();
        statement.getResultSet();
        
        while (statement.getResultSet().next()){
            int id = statement.getResultSet().getInt("id");
            String usuario = statement.getResultSet().getString("usuario");
            String senha = statement.getResultSet().getString("senha");
            
            Usuario usuarioComDadosDoBanco = new Usuario(id, usuario, senha);
            usuarios.add(usuarioComDadosDoBanco);
        }
        return usuarios;
    }
            
            public Usuario selecPortId(Usuario usuario) throws SQLException{
                String sql = "select * usuario where id = ?";
                PreparedStatement statement = connection.prepareStatement(sql);
                statement.setInt(1,usuario.getId());
                
                return  pesquisa(statement).get(0);
            }
            
        
        public boolean existeNoBancoPorUsuarioESenha(Usuario usuario) throws SQLException {
        String sql = "Select * from usuario where usuario  = ? and senha = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, usuario.getUsuario());
            statement.setString(2, usuario.getSenha());
            
            statement.execute();
            
            statement.getResultSet();
            
            return statement.getResultSet().next();
    }
        
}
