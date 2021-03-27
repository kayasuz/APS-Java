/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.Conexao;
import dao.UsuarioDAO;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.Usuario;
import view.CadastroView;

/**
 *
 * @author lucib
 */
public class FormCadastroController {
    
    private CadastroView view;

    public FormCadastroController(CadastroView view) {
        this.view = view;
    }
    
    public void salvaUsuario() {
        
        String userName =  view.getTxtUsuario().getText();
        String senha = view.getTxtSenha().getText();
        String nome =  view.getTxtNome().getText();
        String email = view.getTxtEmail().getText();
        String endereco = view.getTxtEndereco().getText();
        String telefone = view.getTxtTelefone().getText();
        
        Usuario usuario = new Usuario(userName, senha, nome, email, endereco, telefone);
        
        try {
           Connection conexao = new Conexao().getConnection();
           UsuarioDAO usuarioDao = new UsuarioDAO(conexao);
           usuarioDao.insert(usuario);
           
            JOptionPane.showMessageDialog(null, "Usuário Salvo com Sucesso");
            
         } catch (SQLException ex) {
            Logger.getLogger(CadastroView.class.getName()).log(Level.SEVERE, null, ex);
        }
   
    }
    
    
}
