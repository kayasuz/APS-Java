/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Usuario;
import view.MenuViewUsuarios;


public class UsuarioDAO {
    private final Connection connection;

    public UsuarioDAO(Connection connection) {
        this.connection = connection;
    }
    
    public void insert(Usuario usuario) throws SQLException {
        
        String user = usuario.getUsuario();
        String senha = usuario.getSenha();
        String nome = usuario.getNome();
        String email = usuario.getEmail();
        String endereco = usuario.getEndereco();
        String telefone = usuario.getTelefone();
        
        String sql = "insert into usuario(usuario, senha, nome, email, endereco, telefone) values('"+user+"', '"+senha+"', '"+nome+"', '"+email+"', '"+endereco+"', '"+telefone+"');";

        PreparedStatement statement = connection.prepareStatement(sql);
        statement.execute();
        connection.close();
        
    }
    
}
