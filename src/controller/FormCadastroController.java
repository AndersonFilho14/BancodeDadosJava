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
import view.FormeCadastroView;

/**
 *
 * @author filho
 */
public class FormCadastroController {
    
    private FormeCadastroView view;

    public FormCadastroController(FormeCadastroView view) {
        this.view = view;
    }

    public void salvarUsuario(){
        
        
        String usuario = jTextFieldUsuario.getText();
         String senha = jPasswordFieldSenha.getText();
         
         
         Usuario usuarioXandi = new Usuario(usuario,senha);
        try {
            Connection conexao = new Conexao().getConnection();
            UsuarioDAO usuarioDao = new UsuarioDAO(conexao);
            usuarioDao.insert(usuarioXandi);
            
            
        } catch (SQLException ex) {
            System.out.println("Deu errado a conecção");
        }
    }
}
