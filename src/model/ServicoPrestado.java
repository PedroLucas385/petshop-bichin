//Classe de modelo de negócio para representar os serviços prestados no Petshop Bichin

package model;

import java.time.LocalDate;
import java.time.Period;
import java.util.Objects;

public class ServicoPrestado {
    private int id;
    private Servico servico;
    private LocalDate data;
    private double valorCobrado;
    private Pet pet;

    public ServicoPrestado() {
    }

    public ServicoPrestado(int id,Pet pet, Servico servico, double valorCobrado, LocalDate data) {
        this.id = id;
        this.pet = pet;
        this.servico = servico;
        this.valorCobrado = valorCobrado;
        this.data = data;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Servico getServico() {
        return servico;
    }

    public void setServico(Servico servico) {
        this.servico = servico;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public double getvalorCobrado() {
        return valorCobrado;
    }

    public void setvalorCobrado(double valorCobrado) {
        this.valorCobrado = valorCobrado;
    }

    public Pet getPet() {
        return pet;
    }

    public void setPet(Pet pet) {
        this.pet = pet;
    }

    // Validação pra obter os dias do serviço, comparando data informada e a atual
    public int getDiasdaExecucaoDoServico(){
        if (data == null) {
            return 0;
        }
        else {
             return Period.between(this.data, LocalDate.now()).getDays();
        }
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof ServicoPrestado that)) return false;
        return id == that.id && Double.compare(valorCobrado, that.valorCobrado) == 0 && Objects.equals(servico, that.servico) && Objects.equals(data, that.data) && Objects.equals(pet, that.pet);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, servico, data, valorCobrado, pet);
    }

    @Override
    public String toString() {
        return  "id= " + id +
                "\n servico= " + servico +
                "\n data= " + data +
                "\n valorCobrado= " + valorCobrado +
                "\n pet= " + pet;
    }
}

