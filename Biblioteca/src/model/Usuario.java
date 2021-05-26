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
package model;

/**
 *
 * @authors kayasuz, JoaoNodari and lucianabalsaneliscabini
 */
public class Usuario {
    private int id;
    private String usuario;
    private String senha;
    private String nome;
    private String email;
    private String endereco;
    private String telefone;
    private int nrAluguel;
    
    // Login do Usuário
    public Usuario(String usuario, String senha) {
        this.usuario = usuario;
        this.senha = senha;
    }
    
    // Cadastro do Usuário
    public Usuario(int id, String usuario, String senha, String nome, String email, String endereco, String telefone, int nrAluguel) {
        this.id = id;
        this.usuario = usuario;
        this.senha = senha;
        this.nome = nome;
        this.email = email;
        this.endereco = endereco;
        this.telefone = telefone;
        this.nrAluguel = nrAluguel;
    }

    public Usuario(int id, String nome, int nrAluguel) {
        this.id = id;
        this.nome = nome;
        this.nrAluguel = nrAluguel;
    }
    
    //<editor-fold defaultstate="collapsed" desc="Getters e Setters">
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getUsuario() { return usuario; }
    public void setUsuario(String usuario) { this.usuario = usuario; }

    public String getSenha() { return senha; }
    public void setSenha(String senha) { this.senha = senha; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getEndereco() { return endereco; }
    public void setEndereco(String endereco) { this.endereco = endereco; }

    public String getTelefone() { return telefone; }
    public void setTelefone(String telefone) { this.telefone = telefone; }
    
    public int getNrAluguel() { return nrAluguel; }
    public void setNrAluguel(int nrAluguel) { this.nrAluguel = nrAluguel; }
    //</editor-fold>
}
