//<editor-fold defaultstate="collapsed" desc="The MIT License">
/*
 * The MIT License
 *
 * Copyright 2021 Gabriel Pavan de Moura, João da Silva Nodari, 
 * João Guilherme Aragão and Luciana Balsaneli Scabini.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 *///</editor-fold>
package controller;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import dao.Conexao;
import dao.UsuarioDAO;
import model.Usuario;
import view.AdminViewUsuarios;

/**
 *
 * @authors kayas, joaon, lucib, ?
 */
public class FormCadastroController {
    
    private final AdminViewUsuarios view;
    
    public FormCadastroController(AdminViewUsuarios view) { this.view = view; }
    
    public void salvaUsuario() {
        int id = 0;
        String userName = view.getTxtUsuario().getText();
        char[] senha = view.getTxtSenha().getPassword();
        String nome =  view.getTxtNome().getText();
        String email = view.getTxtEmail().getText();
        String endereco = view.getTxtEndereco().getText();
        String telefone = view.getTxtTelefone().getText();
        
        Usuario usuario = new Usuario(0, userName, senha, nome, email, endereco, telefone);
        
        try {
            Connection conexao = new Conexao().getConnection();
            UsuarioDAO usuarioDao = new UsuarioDAO(conexao);
            usuarioDao.insert(usuario);
            JOptionPane.showMessageDialog(null, "Usuário salvo com sucesso.");
        }catch(SQLException ex) {
            Logger.getLogger(AdminViewUsuarios.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
