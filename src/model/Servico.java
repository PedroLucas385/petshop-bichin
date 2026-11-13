//Classe de modelo de negócio para representar os serviços disponibilizados no Petshop Bichin
package model;

import java.util.Objects;

public class Servico {
    private String descricao;
    private int id;
    private double valor;

    public Servico(){}

    public Servico(int id, String descricao, double valor){
        this.descricao = descricao;
        this.id = id;
        this.valor = valor;
    }

    public String getDescricao(){
        return descricao;
    }

    public void setDescricao(String descricao){
        this.descricao = descricao;
    }

    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Servico servico)) return false;
        return id == servico.id && Double.compare(valor, servico.valor) == 0 && Objects.equals(descricao, servico.descricao);
    }

    @Override
    public int hashCode() {
        return Objects.hash(descricao, id, valor);
    }

    @Override
    public String toString() {
        return "ID do serviço: " + id + "\n" +
                "Descrição do serviço: " + descricao + "\n" +
                "Valor do serviço: " + valor + "\n";
    }
}


