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

import model.Categoria;
import dao.Conexao;
import dao.UsuarioDAO;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import model.Autor;
import model.Livro;
import view.AdminView;

/**
 *
 * @authors kayasuz, JoaoNodari and lucianabalsaneliscabini
 */
public class LivrosController {
    private final AdminView cardLivros;

    public LivrosController(AdminView cardLivros) { this.cardLivros = cardLivros; }
    
    public void adicionaCapa() {
        int response = cardLivros.getFileChooser().showOpenDialog(null);
        if (response == JFileChooser.APPROVE_OPTION) {
            String pathName = cardLivros.getFileChooser().getSelectedFile().getPath();
            ImageIcon icon = new ImageIcon(pathName);
            cardLivros.getLbCapaLivro().setIcon(icon);
        } else { JOptionPane.showMessageDialog(null, "Capa do livro obrigatória"); }
    }
        
    public void salvaLivro() {
        int id = Integer.parseInt(cardLivros.getTxtIdUsuario().getText());
        String nome = cardLivros.getTxtNomeLivro().getText();
        String publicacao = cardLivros.getTxtPublicacaoLivro().getText();
        String resumo = cardLivros.getTxtAreaResumoLivro().getText();
        int quantidade = (int) cardLivros.getSpnQuantidadeLivro().getValue();
        String categoria =  cardLivros.getCmbBoxCategoriaLivro().getSelectedItem().toString();
        String autor = cardLivros.getCmbBoxAutorLivro().getSelectedItem().toString();
        String capa = cardLivros.getFileChooser().getSelectedFile().getPath();

        Livro livro = new Livro(id, nome, Integer.parseInt(publicacao), 
                                resumo, quantidade, Integer.parseInt(categoria), 
                                Integer.parseInt(autor), capa);
        
        try {
            Connection conexao = new Conexao().getConnection();
            UsuarioDAO usuarioDao = new UsuarioDAO(conexao);
            usuarioDao.insertLivro(livro);
            JOptionPane.showMessageDialog(null, "Livro salvo com sucesso");
        }catch(SQLException ex) {
            Logger.getLogger(AdminView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void excluiLivro() { // mover para EmprestimosController
        int id = Integer.parseInt(cardLivros.getTxtIdUsuario().getText());
        String nome = cardLivros.getTxtNomeLivro().getText();
        String publicacao = cardLivros.getTxtPublicacaoLivro().getText();
        String resumo = cardLivros.getTxtAreaResumoLivro().getText();
        int quantidade = (int) cardLivros.getSpnQuantidadeLivro().getValue();
        String categoria =  cardLivros.getCmbBoxCategoriaLivro().getSelectedItem().toString();
        String autor = cardLivros.getCmbBoxAutorLivro().getSelectedItem().toString();
        String capa = cardLivros.getFileChooser().getSelectedFile().getPath();
        
        Livro livro = new Livro(id, nome, Integer.parseInt(publicacao), 
                                resumo, quantidade, Integer.parseInt(categoria), 
                                Integer.parseInt(autor), capa);
        
        try {
            Connection conexao = new Conexao().getConnection();
            UsuarioDAO usuarioDao = new UsuarioDAO(conexao);
            usuarioDao.deleteLivro(livro);
            JOptionPane.showMessageDialog(null, "Livro excluído com sucesso");
        }catch(SQLException ex) {
            Logger.getLogger(AdminView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void mostraCategorias() {
        DefaultTableModel model = (DefaultTableModel) cardLivros.getTbCategorias().getModel();
        model.setRowCount(0);
        
        try {
            Connection conexao = new Conexao().getConnection();
            UsuarioDAO usuarioDao = new UsuarioDAO(conexao);
            ArrayList<Categoria> list = usuarioDao.selectAllCategoria();
            Object[] row = new Object [2];
            for(int i = 0; i < list.size(); i++) {
                row[0] = list.get(i).getId();
                row[1] = list.get(i).getNome();
                model.addRow(row);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UsuariosController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        DefaultComboBoxModel cmbC = (DefaultComboBoxModel) cardLivros.getCmbBoxCategoriaLivro().getModel();
        cmbC.removeAllElements();
        
        try {
            Connection conexao = new Conexao().getConnection();
            UsuarioDAO usuarioDao = new UsuarioDAO(conexao);
            ArrayList<Categoria> list = usuarioDao.selectAllCategoria();
                for(int i = 0; i <= list.size(); i++) {
                list.get(0).getId();
                cmbC.insertElementAt(cardLivros.getCmbBoxCategoriaLivro().getModel().getSize(), i);
            }
            cmbC.setSelectedItem(0);
        } catch (SQLException ex) {
            Logger.getLogger(UsuariosController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void mostraAutores() {
        DefaultTableModel model = (DefaultTableModel) cardLivros.getTbAutores().getModel();
        model.setRowCount(0);
        try {
            Connection conexao = new Conexao().getConnection();
            UsuarioDAO usuarioDao = new UsuarioDAO(conexao);
            ArrayList<Autor> list = usuarioDao.selectAllAutor();
            Object[] row = new Object [2];
            for(int i = 0; i < list.size(); i++) {
                row[0] = list.get(i).getId();
                row[1] = list.get(i).getNome();
                model.addRow(row);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UsuariosController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        DefaultComboBoxModel cmbA = (DefaultComboBoxModel) cardLivros.getCmbBoxAutorLivro().getModel();
        cmbA.removeAllElements();
        try {
            Connection conexao = new Conexao().getConnection();
            UsuarioDAO usuarioDao = new UsuarioDAO(conexao);
            ArrayList<Autor> list = usuarioDao.selectAllAutor();
                for(int i = 0; i <= list.size(); i++) {
                list.get(0).getId();
                cmbA.insertElementAt(cardLivros.getCmbBoxAutorLivro().getModel().getSize(), i);
            }
            cmbA.setSelectedItem(0);
        } catch (SQLException ex) {
            Logger.getLogger(UsuariosController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void selecionaCategorias() {
        JTable table = cardLivros.getTbCategorias();
        int row = table.getSelectedRow();
        JTextField[] get = new JTextField [2];
        
        get[0] = cardLivros.getTxtIdCategoria();
        get[1] = cardLivros.getTxtCategoria();
        
        for (int i = 0; i < get.length; i++) {
            get[i].setText(table.getValueAt(row, i).toString());
        }
    }
    
    public void selecionaAutores() {
        JTable table = cardLivros.getTbAutores();
        int row = table.getSelectedRow();
        JTextField[] get = new JTextField [2];
        
        get[0] = cardLivros.getTxtIdAutor();
        get[1] = cardLivros.getTxtAutor();
        
        for (int i = 0; i < get.length; i++) {
            get[i].setText(table.getValueAt(row, i).toString());
        }
    }
    
    public void limpaCamposCategorias() {
        cardLivros.getTxtIdCategoria().setText("0");
        cardLivros.getTxtCategoria().setText(null);
    }
    
    public void limpaCamposAutores() {
        cardLivros.getTxtIdAutor().setText("0");
        cardLivros.getTxtAutor().setText(null);
    }
    
    public void adicionaCategoria() {
        int id = Integer.parseInt(cardLivros.getTxtIdCategoria().getText());
        String nome = cardLivros.getTxtCategoria().getText();
        
        Categoria categoria = new Categoria(id, nome);
        
        try {
            Connection conexao = new Conexao().getConnection();
            UsuarioDAO usuarioDao = new UsuarioDAO(conexao);
            usuarioDao.insertOrUpdateCategoria(categoria);
            mostraCategorias();
            JOptionPane.showMessageDialog(null, "Categoria salva com sucesso");
            limpaCamposCategorias();
        }catch(SQLException ex) {
            Logger.getLogger(AdminView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void excluiCategoria() {
        int id = Integer.parseInt(cardLivros.getTxtIdCategoria().getText());
        String nome = cardLivros.getTxtCategoria().getText();
        
        Categoria categoria = new Categoria(id, nome);
        
        try {
            Connection conexao = new Conexao().getConnection();
            UsuarioDAO usuarioDao = new UsuarioDAO(conexao);
            usuarioDao.deleteCategoria(categoria);
            mostraCategorias();
            JOptionPane.showMessageDialog(null, "Categoria excluída com sucesso");
        }catch(SQLException ex) {
            Logger.getLogger(AdminView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void adicionaAutor() {
        int id = Integer.parseInt(cardLivros.getTxtIdAutor().getText());
        String nome = cardLivros.getTxtAutor().getText();
        
        Autor autor = new Autor(id, nome);
        
        try {
            Connection conexao = new Conexao().getConnection();
            UsuarioDAO usuarioDao = new UsuarioDAO(conexao);
            usuarioDao.insertOrUpdateAutor(autor);
            mostraAutores();
            JOptionPane.showMessageDialog(null, "Autor salvo com sucesso");
            limpaCamposAutores();
        }catch(SQLException ex) {
            Logger.getLogger(AdminView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void excluiAutor() {
        int id = Integer.parseInt(cardLivros.getTxtIdAutor().getText());
        String nome = cardLivros.getTxtAutor().getText();
        
        Autor autor = new Autor(id, nome);
        
        try {
            Connection conexao = new Conexao().getConnection();
            UsuarioDAO usuarioDao = new UsuarioDAO(conexao);
            usuarioDao.deleteAutor(autor);
            mostraAutores();
            JOptionPane.showMessageDialog(null, "Autor excluído com sucesso");
        }catch(SQLException ex) {
            Logger.getLogger(AdminView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
