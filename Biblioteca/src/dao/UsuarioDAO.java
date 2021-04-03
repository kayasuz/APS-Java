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
import java.util.ArrayList;
import java.util.Arrays;
import model.Usuario;

/**
 *
 * @authors kayas, joaon, lucib, ?
 */
public class UsuarioDAO {
    
    private final Connection connection;

    public UsuarioDAO(Connection connection) { this.connection = connection; }
    
    public void insert(Usuario usuario) throws SQLException {
        String sql = "INSERT INTO usuario(usuario, senha, nome, email, endereco, telefone) " +
                     "VALUES(?, ?, ?, ?, ?, ?);";
        PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, usuario.getUsuario());
            statement.setString(2, Arrays.toString(usuario.getSenha()));
            statement.setString(3, usuario.getNome());
            statement.setString(4, usuario.getEmail());
            statement.setString(5, usuario.getEndereco());
            statement.setString(6, usuario.getTelefone());
            statement.execute();
    }

    public void update(Usuario usuario) throws SQLException {
        String sql = "UPDATE usuario SET usuario = ?, senha = ?, nome = ?, email = ?, endereco = ?, telefone = ? " +
                     "WHERE id = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, usuario.getUsuario());
            statement.setString(2, Arrays.toString(usuario.getSenha()));
            statement.setString(3, usuario.getNome());
            statement.setString(4, usuario.getEmail());
            statement.setString(5, usuario.getEndereco());
            statement.setString(6, usuario.getTelefone());
            statement.setInt(7, usuario.getId());
            statement.execute();
    }
    
    public void insertOrUpdate(Usuario usuario) throws SQLException {
        if(usuario.getId() > 0) { update(usuario); }
        else { insert(usuario); }
    }

    public void delete(Usuario usuario) throws SQLException {
        String sql = "DELETE FROM usuario WHERE id = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, usuario.getId());
            statement.execute();
    }

    public ArrayList<Usuario> selectAll() throws SQLException{
        String sql = "SELECT * FROM usuario";
        PreparedStatement statement = connection.prepareStatement(sql);
        
        return pesquisa(statement);
    }

    private ArrayList<Usuario> pesquisa(PreparedStatement statement) throws SQLException {
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
            
            Usuario usuarioComDadosDoBanco = new Usuario(id, usuario, senha.toCharArray(), nome, email, endereco, telefone);
            usuarios.add(usuarioComDadosDoBanco);
        }
        
        return usuarios;
    }

    public Usuario selectPorId(Usuario usuario) throws SQLException {
        String sql = "SELECT * FROM usuario WHERE id = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, usuario.getId());
        return pesquisa(statement).get(0);
    }

    public boolean existeNoBancoPorUsuarioESenha(Usuario usuario) throws SQLException {
        String sql = "SELECT * FROM usuario WHERE usuario = ? AND senha = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, usuario.getUsuario());
            statement.setString(2, Arrays.toString(usuario.getSenha()));
            statement.execute();
        
        ResultSet resultSet = statement.getResultSet();
        return resultSet.next();
    }
}
