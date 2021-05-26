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

import dao.Conexao;
import dao.UsuarioDAO;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.Usuario;
import view.AdminView;
import view.LoginView;
import view.UserView;

/**
 *
 * @authors kayasuz, JoaoNodari and lucianabalsaneliscabini
 */
public class LoginController {
    private final LoginView view;
    
    public LoginController(LoginView view) { this.view = view; }
    
    public void autenticar() {
        String userName =  view.getTxtUsuario().getText();
        String senha = view.getTxtSenha().getText();
        Usuario usuarioAutenticar = new Usuario(userName, senha);
        
        try {
            Connection conexao = new Conexao().getConnection();
            UsuarioDAO usuarioDao = new UsuarioDAO(conexao);
            
            boolean existe = usuarioDao.existeNoBancoPorUsuarioESenha(usuarioAutenticar);
            
            if(existe) {
                view.dispose();
                if(userName.equals("admin") && senha.equals("admin")) { new AdminView().setVisible(true); } 
                else {
                    new UserView().setVisible(true);
                }
            }else {
                JOptionPane.showMessageDialog(view, "Usuário e/ou senha inválidos.");
                view.getTxtUsuario().setText(null);
                view.getTxtSenha().setText(null);
            }
        }catch(SQLException ex) {
            Logger.getLogger(AdminView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
