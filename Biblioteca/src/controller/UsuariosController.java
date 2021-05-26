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
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import model.Usuario;
import view.AdminView;
import view.LoginView;

/**
 *
 * @authors kayasuz, JoaoNodari and lucianabalsaneliscabini
 */
public class UsuariosController {
    private final AdminView cardUsuarios;
    
    public UsuariosController(AdminView cardUsuarios) { this.cardUsuarios = cardUsuarios; }
    
    public void mostraUsuarios() {
        DefaultTableModel model = (DefaultTableModel) cardUsuarios.getTbUsuarios().getModel();
        model.setRowCount(0);
        
        try {
            Connection conexao = new Conexao().getConnection();
            UsuarioDAO usuarioDao = new UsuarioDAO(conexao);
            ArrayList<Usuario> list = usuarioDao.selectAllUsuario();
            Object[] row = new Object [7];
            for(int i = 0; i < list.size(); i++) {
                row[0] = list.get(i).getId();
                row[1] = list.get(i).getNome();
                row[2] = list.get(i).getEmail();
                row[3] = list.get(i).getEndereco();
                row[4] = list.get(i).getUsuario();
                row[5] = list.get(i).getSenha();
                row[6] = list.get(i).getTelefone();
                model.addRow(row);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UsuariosController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void selecionaUsuario() {
        JTable table = cardUsuarios.getTbUsuarios();
        int row = table.getSelectedRow();
        JTextField[] get = new JTextField [7];
        
        get[0] = cardUsuarios.getTxtIdUsuario();
        get[1] = cardUsuarios.getTxtNomeUsuario();
        get[2] = cardUsuarios.getTxtEmailUsuario();
        get[3] = cardUsuarios.getTxtEnderecoUsuario();
        get[4] = cardUsuarios.getTxtUsuario();
        get[5] = cardUsuarios.getTxtSenhaUsuario();
        get[6] = cardUsuarios.getTxtTelefoneUsuario();
        
        for (int i = 0; i < get.length; i++) {
            get[i].setText(table.getValueAt(row, i).toString());
        }
    }
    
    public void limpaCampos() {
        cardUsuarios.getTxtIdUsuario().setText("0");
        cardUsuarios.getTxtNomeUsuario().setText(null);
        cardUsuarios.getTxtEmailUsuario().setText(null);
        cardUsuarios.getTxtEnderecoUsuario().setText(null);
        cardUsuarios.getTxtUsuario().setText(null);
        cardUsuarios.getTxtSenhaUsuario().setText(null);
        cardUsuarios.getTxtTelefoneUsuario().setText(null);
    }
    
    public void salvaUsuario() {
        int id = Integer.parseInt(cardUsuarios.getTxtIdUsuario().getText());
        String userName = cardUsuarios.getTxtUsuario().getText();
        String senha = cardUsuarios.getTxtSenhaUsuario().getText();
        String nome =  cardUsuarios.getTxtNomeUsuario().getText();
        String email = cardUsuarios.getTxtEmailUsuario().getText();
        String endereco = cardUsuarios.getTxtEnderecoUsuario().getText();
        String telefone = cardUsuarios.getTxtTelefoneUsuario().getText();
        int nrAluguel = Integer.parseInt(cardUsuarios.getTxtTelefoneUsuario().getText());
        
        Usuario usuario = new Usuario(id, userName, senha, nome, email, endereco, telefone, nrAluguel);
        
        try {
            Connection conexao = new Conexao().getConnection();
            UsuarioDAO usuarioDao = new UsuarioDAO(conexao);
            usuarioDao.insertOrUpdateUsuario(usuario);
            mostraUsuarios();
            JOptionPane.showMessageDialog(null, "Dados salvos com sucesso");
        }catch(SQLException ex) {
            Logger.getLogger(AdminView.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        limpaCampos();
    }
    
    public void excluiUsuario() {
        int id = Integer.parseInt(cardUsuarios.getTxtIdUsuario().getText());
        String userName = cardUsuarios.getTxtUsuario().getText();
        String senha = cardUsuarios.getTxtSenhaUsuario().getText();
        String nome =  cardUsuarios.getTxtNomeUsuario().getText();
        String email = cardUsuarios.getTxtEmailUsuario().getText();
        String endereco = cardUsuarios.getTxtEnderecoUsuario().getText();
        String telefone = cardUsuarios.getTxtTelefoneUsuario().getText();
        int nrAluguel = Integer.parseInt(cardUsuarios.getTxtTelefoneUsuario().getText());
        
        Usuario usuario = new Usuario(id, userName, senha, nome, email, endereco, telefone, nrAluguel);
        
        try {
            Connection conexao = new Conexao().getConnection();
            UsuarioDAO usuarioDao = new UsuarioDAO(conexao);
            usuarioDao.deleteUsuario(usuario);
            mostraUsuarios();
            JOptionPane.showMessageDialog(null, "Usuário excluído");
        }catch(SQLException ex) {
            Logger.getLogger(AdminView.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        limpaCampos();
    }
    
    public void sair() {
        int option = JOptionPane.showConfirmDialog(
            cardUsuarios, 
            "Deseja realmente sair?",
            "Biblioteca JJLG",
            JOptionPane.YES_NO_OPTION, 
            JOptionPane.QUESTION_MESSAGE
        );
        
        if (option == JOptionPane.YES_OPTION) {
            cardUsuarios.dispose();
            new LoginView().setVisible(true);
        }
    }
}
