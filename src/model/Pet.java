//Classe de modelo de negócio para representar os pets atendidos no Petshop Bichin
package model;

import java.time.LocalDate;
import java.time.Period;
import java.util.Objects;

public class Pet {
    private int id;
    private String nome;
    private String especie;
    private String raca;
    private Cliente cliente;
    private LocalDate nascimento;

    public Pet() {
        this.cliente = new Cliente();
    }

    public Pet(int id, String nome, String especie, String raca, LocalDate nascimento, Cliente cliente) {
        this.id = id;
        this.nome = nome;
        this.especie = especie;
        this.raca = raca;
        this.nascimento = nascimento;
        this.cliente = cliente;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEspecie() {
        return especie;
    }

    public void setEspecie(String especie) {
        this.especie = especie;
    }

    public String getRaca() {
        return raca;
    }

    public void setRaca(String raca) {
        this.raca = raca;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public LocalDate getNascimento() {
        return nascimento;
    }

    public void setNascimento(LocalDate nascimento) {
        this.nascimento = nascimento;
    }

    // Obtenção da idade do pet de acordo com seu nascimento e data atual
    public int getIdade(){
        if (this.nascimento == null) {
            return 0;
        }
        else {
            return Period.between(this.nascimento, LocalDate.now()).getYears();
        }
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Pet pet)) return false;
        return id == pet.id && Objects.equals(nome, pet.nome) && Objects.equals(especie, pet.especie) && Objects.equals(raca, pet.raca) && Objects.equals(cliente, pet.cliente) && Objects.equals(nascimento, pet.nascimento);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome, especie, raca, cliente, nascimento);
    }

    @Override
    public String toString() {
        return  "ID do pet: " + id +
                "\n Nome: " + nome +
                "\n Espécie: " + especie +
                "\n Raça: " + raca +
                "\n Cliente associado: " + cliente +
                "\n Idade: " + nascimento;
    }
}
