//<editor-fold defaultstate="collapsed" desc="The MIT License">
/*
 * The MIT License
 *
 * Copyright 2021 kayas.
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
import javax.swing.table.DefaultTableModel;
import model.Emprestimo;
import model.Livro;
import model.Usuario;
import view.AdminView;

/**
 *
 * @authors kayasuz, JoaoNodari and lucianabalsaneliscabini
 */
public class EmprestimosController {
    private final AdminView cardEmprestimos;

    public EmprestimosController(AdminView cardEmprestimos) { this.cardEmprestimos = cardEmprestimos; }
    
    public void mostraClientes() {
        DefaultTableModel model = (DefaultTableModel) cardEmprestimos.getTbClientes().getModel();
        model.setRowCount(0);
        
        try {
            Connection conexao = new Conexao().getConnection();
            UsuarioDAO usuarioDao = new UsuarioDAO(conexao);
            ArrayList<Usuario> list = usuarioDao.selectAllCliente();
            Object[] row = new Object [3];
            for(int i = 0; i < list.size(); i++) {
                row[0] = list.get(i).getId();
                row[1] = list.get(i).getNome();
                row[2] = list.get(i).getNrAluguel();
                model.addRow(row);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UsuariosController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void mostraLivros() {
        DefaultTableModel model = (DefaultTableModel) cardEmprestimos.getTbLivros().getModel();
        model.setRowCount(0);
        
        try {
            Connection conexao = new Conexao().getConnection();
            UsuarioDAO usuarioDao = new UsuarioDAO(conexao);
            ArrayList<Livro> list = usuarioDao.selectAllLivro();
            Object[] row = new Object [3];
            for(int i = 0; i < list.size(); i++) {
                row[0] = list.get(i).getId();
                row[1] = list.get(i).getNome();
                row[2] = list.get(i).getQuantidade();
                model.addRow(row);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UsuariosController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void salvar() {
        int idUsuario = Integer.parseInt(cardEmprestimos.getTxtNomeCliente().getText());
        int idLivro = Integer.parseInt(cardEmprestimos.getTxtLivroCliente().getText());
        Emprestimo emprestimo = new Emprestimo(idUsuario, idLivro);
        
        try {
            Connection conexao = new Conexao().getConnection();
            UsuarioDAO usuarioDao = new UsuarioDAO(conexao);
            usuarioDao.insertEmprestimo(emprestimo);
            JOptionPane.showMessageDialog(null, "Empréstimo computado com sucesso.");
        }catch(SQLException ex) {
            Logger.getLogger(AdminView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
