//Classe de modelo de negócio para representar os clientes atendidos no Petshop Bichin
package model;

import java.util.Objects;

public class Cliente {
    private String nome;
    private int id;
    private String telefone;

    public Cliente() {}

    public Cliente(int id, String nome, String telefone) {
        this.id = id;
        this.nome = nome;
        this.setTelefone(telefone);
    }

    public String getNome(){
        return nome;
    }

    public void setNome(String nome){
        this.nome = nome;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTelefone() {
        return telefone;
    }

    // Validação do telefone no formato correto/padrão brasileiro
    public void setTelefone(String telefone) {
        if(telefone.matches("\\(\\d{2}\\) \\d{5}-\\d{5}")) {
            this.telefone = telefone;
        }
        else {
            throw new IllegalArgumentException("Respeite o formato padrão de telefone - (dd) ddddd-ddddd");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Cliente cliente)) return false;
        return id == cliente.id && Objects.equals(nome, cliente.nome) && Objects.equals(telefone, cliente.telefone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome, id, telefone);
    }

    @Override
    public String toString(){
        return "ID Cliente: " + id + "\n" +
                "Nome: " + nome + "\n" +
                "Telefone: " + telefone;
    }
}
