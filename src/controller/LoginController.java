/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import DAO.Conexao;
import DAO.UsuarioDAO;
import Model.Usuario;
import java.sql.Connection;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import view.LoginView;
import view.MenuView;

/**
 *
 * @author filho
 */
public class LoginController {
    private LoginView view;

    public LoginController(LoginView view) {
        this.view = view;
    }

    public void autenticar() throws SQLException {
        
//buscar usuario na view
        String usuario = view.getjTextFieldUsuario().getText();
        String senha  = view.getjPasswordFieldSenha().getText();
        
        Usuario usuarioautenticar = new Usuario(usuario, senha);
        
// Verificar se existe no banco de dados
            Connection conexao = new Conexao().getConnection();
            UsuarioDAO usuarioDao = new UsuarioDAO(conexao);
           
            boolean existe = usuarioDao.existeNoBancoPorUsuarioESenha(usuarioautenticar);
            
//se tiver, direcione para o menu
        if(existe){
        MenuView telaDeMenu  = new MenuView();
        telaDeMenu.setVisible(true);
            }else{
                JOptionPane.showMessageDialog(view, "Usuario ou senha invalidos");
            }
     
    }
    
    
}
