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
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import model.Autor;
import model.Categoria;
import model.Emprestimo;
import model.Livro;
import model.Usuario;

/**
 *
 * @authors kayasuz, JoaoNodari and lucianabalsaneliscabini
 */
public class UsuarioDAO {
    private final Connection connection;
    
    public UsuarioDAO(Connection connection) { this.connection = connection; }
    
    //<editor-fold defaultstate="collapsed" desc="Classe Usuario">
    public Usuario insertUsuario(Usuario usuario) throws SQLException {
        String sql = "INSERT INTO usuario(usuario, senha, nome, email, endereco, telefone, nr_alugueis) " +
                     "VALUES(?, ?, ?, ?, ?, ?, ?);";
        PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, usuario.getUsuario());
            statement.setString(2, usuario.getSenha());
            statement.setString(3, usuario.getNome());
            statement.setString(4, usuario.getEmail());
            statement.setString(5, usuario.getEndereco());
            statement.setString(6, usuario.getTelefone());
            statement.setInt(7, usuario.getNrAluguel());
            statement.execute();
            
        ResultSet resultSet = statement.getGeneratedKeys();
            if(resultSet.next()) {
                int id = resultSet.getInt("id");
                usuario.setId(id);
            }
        return usuario;
    }

    public void updateUsuario(Usuario usuario) throws SQLException {
        String sql = "UPDATE usuario SET usuario = ?, senha = ?, nome = ?, email = ?, endereco = ?, telefone = ?, nr_alugueis = ? " +
                     "WHERE id = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, usuario.getUsuario());
            statement.setString(2, usuario.getSenha());
            statement.setString(3, usuario.getNome());
            statement.setString(4, usuario.getEmail());
            statement.setString(5, usuario.getEndereco());
            statement.setString(6, usuario.getTelefone());
            statement.setInt(7, usuario.getNrAluguel());
            statement.setInt(8, usuario.getId());
            statement.execute();
    }
    
    public void insertOrUpdateUsuario(Usuario usuario) throws SQLException {
        if(usuario.getId() > 0) { updateUsuario(usuario); }
        else { insertUsuario(usuario); }
    }

    public void deleteUsuario(Usuario usuario) throws SQLException {
        String sql = "DELETE FROM usuario WHERE id = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, usuario.getId());
            statement.execute();
    }

    public ArrayList<Usuario> selectAllUsuario() throws SQLException{
        String sql = "SELECT * FROM usuario ORDER BY id";
        PreparedStatement statement = connection.prepareStatement(sql);
        
        return pesquisaUsuario(statement);
    }

    private ArrayList<Usuario> pesquisaUsuario(PreparedStatement statement) throws SQLException {
        ArrayList<Usuario> usuarios = new ArrayList<>();
        
        statement.execute();
        ResultSet resultSet = statement.getResultSet();
        
        while(resultSet.next()) {
            int id = resultSet.getInt("id");
            String usuario = resultSet.getString("usuario");
            String senha = resultSet.getString("senha");
            String nome = resultSet.getString("nome");
            String email = resultSet.getString("email");
            String endereco = resultSet.getString("endereco");
            String telefone = resultSet.getString("telefone");
            int nrAluguel = resultSet.getInt("nr_alugueis");
            
            Usuario usuarioComDadosDoBanco = new Usuario(id, usuario, senha, nome, email, endereco, telefone, nrAluguel);
            usuarios.add(usuarioComDadosDoBanco);
        }
        
        return usuarios;
    }

    public Usuario selectPorId(Usuario usuario) throws SQLException {
        String sql = "SELECT * FROM usuario WHERE id = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, usuario.getId());
        return pesquisaUsuario(statement).get(0);
    }

    public boolean existeNoBancoPorUsuarioESenha(Usuario usuario) throws SQLException {
        String sql = "SELECT * FROM usuario WHERE usuario = ? AND senha = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, usuario.getUsuario());
            statement.setString(2, usuario.getSenha());
            statement.execute();
        
        ResultSet resultSet = statement.getResultSet();
        return resultSet.next();
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Classe Emprestimo">
    public Emprestimo insertEmprestimo(Emprestimo emprestimo) throws SQLException {
        String sql = "INSERT INTO emprestimo(id_livro, id_usuario) " +
                     "VALUES(?, ?);";
        PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, emprestimo.getId_livro());
            statement.setInt(2, emprestimo.getId_usuario());
            statement.execute();
            
        ResultSet resultSet = statement.getGeneratedKeys();
            if(resultSet.next()) {
                int id = resultSet.getInt("id");
                emprestimo.setId(id);
            }
        return emprestimo;
    }
    
    public ArrayList<Usuario> selectAllCliente() throws SQLException {
        String sql = "SELECT * FROM usuario ORDER BY id";
        PreparedStatement statement = connection.prepareStatement(sql);
        
        return pesquisaCliente(statement);
    }
    
    private ArrayList<Usuario> pesquisaCliente(PreparedStatement statement) throws SQLException {
        ArrayList<Usuario> clientes = new ArrayList<>();
        
        statement.execute();
        ResultSet resultSet = statement.getResultSet();
        
        while(resultSet.next()) {
            int id = resultSet.getInt("id");
            String nome = resultSet.getString("nome");
            int nrAluguel = resultSet.getInt("nr_alugueis");
            
            Usuario clienteComDadosDoBanco = new Usuario(id, nome, nrAluguel);
            clientes.add(clienteComDadosDoBanco);
        }
        
        return clientes;
    }
    
    public ArrayList<Livro> selectAllLivro() throws SQLException {
        String sql = "SELECT * FROM livro ORDER BY id";
        PreparedStatement statement = connection.prepareStatement(sql);
        
        return pesquisaLivro(statement);
    }
    
    private ArrayList<Livro> pesquisaLivro(PreparedStatement statement) throws SQLException {
        ArrayList<Livro> livros = new ArrayList<>();
        
        statement.execute();
        ResultSet resultSet = statement.getResultSet();
        
        while(resultSet.next()) {
            int id = resultSet.getInt("id");
            String nome = resultSet.getString("nome");
            int quantidade = resultSet.getInt("quantidade");
            
            Livro livroComDadosDoBanco = new Livro(id, nome, quantidade);
            livros.add(livroComDadosDoBanco);
        }
        
        return livros;
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Classe Livro">
    public Livro insertLivro(Livro livro) throws SQLException {
        String sql = "INSERT INTO livro(nome, publicacao, resumo, quantidade, categoria, autor, capa) " +
                     "VALUES(?, ?, ?, ?, ?, ?, ?);";
        PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, livro.getNome());
            statement.setInt(2, livro.getPublicacao());
            statement.setString(3, livro.getResumo());
            statement.setInt(4, livro.getQuantidade());
            statement.setInt(5, livro.getCategoria());
            statement.setInt(6, livro.getAutor());
            statement.setString(7, livro.getCapa());
            statement.execute();
            
        ResultSet resultSet = statement.getGeneratedKeys();
            if(resultSet.next()) {
                int id = resultSet.getInt("id");
                livro.setId(id);
            }
        return livro;
    }
    
    public void updateLivro(Livro livro) throws SQLException {
        String sql = "UPDATE livro SET nome = ?, publicacao = ?, resumo = ?, quantidade = ?, categoria = ?, autor = ?, capa = ? " +
                     "WHERE id = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, livro.getNome());
            statement.setInt(2, livro.getPublicacao());
            statement.setString(3, livro.getResumo());
            statement.setInt(4, livro.getQuantidade());
            statement.setInt(5, livro.getCategoria());
            statement.setInt(6, livro.getAutor());
            statement.setString(7, livro.getCapa());
            statement.setInt(8, livro.getId());
            statement.execute();
    }
    
    public void insertOrUpdateLivro(Livro livro) throws SQLException {
        if(livro.getId() > 0) { updateLivro(livro); }
        else { insertLivro(livro); }
    }
    
    public void deleteLivro(Livro livro) throws SQLException {
        String sql = "DELETE FROM livro WHERE id = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, livro.getId());
            statement.execute();
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Classe Categoria">
    public Categoria insertCategoria(Categoria categoria) throws SQLException {
        String sql = "INSERT INTO categoria(nome) VALUES(?);";
        PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, categoria.getNome());
            statement.execute();
            
        ResultSet resultSet = statement.getGeneratedKeys();
            if(resultSet.next()) {
                int id = resultSet.getInt("id");
                categoria.setId(id);
            }
        return categoria;
    }
    
    public void updateCategoria(Categoria categoria) throws SQLException {
        String sql = "UPDATE categoria SET nome = ? WHERE id = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, categoria.getNome());
            statement.setInt(2, categoria.getId());
            statement.execute();
    }
    
    public void insertOrUpdateCategoria(Categoria categoria) throws SQLException {
        if(categoria.getId() > 0) { updateCategoria(categoria); }
        else { insertCategoria(categoria); }
    }
    
    public void deleteCategoria(Categoria categoria) throws SQLException {
        String sql = "DELETE FROM categoria WHERE id = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, categoria.getId());
            statement.execute();
    }
    
    public ArrayList<Categoria> selectAllCategoria() throws SQLException{
        String sql = "SELECT * FROM categoria ORDER BY id";
        PreparedStatement statement = connection.prepareStatement(sql);
        
        return pesquisaCategoria(statement);
    }
    
    private ArrayList<Categoria> pesquisaCategoria(PreparedStatement statement) throws SQLException {
        ArrayList<Categoria> categorias = new ArrayList<>();
        
        statement.execute();
        ResultSet resultSet = statement.getResultSet();
        
        while(resultSet.next()) {
            int id = resultSet.getInt("id");
            String nome = resultSet.getString("nome");
            
            Categoria categoriaComDadosDoBanco = new Categoria(id, nome);
            categorias.add(categoriaComDadosDoBanco);
        }
        
        return categorias;
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Classe Autor">
    public Autor insertAutor(Autor autor) throws SQLException {
        String sql = "INSERT INTO autor(nome) VALUES(?);";
        PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, autor.getNome());
            statement.execute();
            
        ResultSet resultSet = statement.getGeneratedKeys();
            if(resultSet.next()) {
                int id = resultSet.getInt("id");
                autor.setId(id);
            }
        return autor;
    }
    
    public void updateAutor(Autor autor) throws SQLException {
        String sql = "UPDATE autor SET nome = ? WHERE id = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, autor.getNome());
            statement.setInt(2, autor.getId());
            statement.execute();
    }
    
    public void insertOrUpdateAutor(Autor autor) throws SQLException {
        if(autor.getId() > 0) { updateAutor(autor); }
        else { insertAutor(autor); }
    }
    
    public void deleteAutor(Autor autor) throws SQLException {
        String sql = "DELETE FROM autor WHERE id = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, autor.getId());
            statement.execute();
    }
    
    public ArrayList<Autor> selectAllAutor() throws SQLException{
        String sql = "SELECT * FROM autor ORDER BY id";
        PreparedStatement statement = connection.prepareStatement(sql);
        
        return pesquisaAutor(statement);
    }
    
    private ArrayList<Autor> pesquisaAutor(PreparedStatement statement) throws SQLException {
        ArrayList<Autor> autores = new ArrayList<>();
        
        statement.execute();
        ResultSet resultSet = statement.getResultSet();
        
        while(resultSet.next()) {
            int id = resultSet.getInt("id");
            String nome = resultSet.getString("nome");
            
            Autor autorComDadosDoBanco = new Autor(id, nome);
            autores.add(autorComDadosDoBanco);
        }
        
        return autores;
    }
    //</editor-fold>
}
