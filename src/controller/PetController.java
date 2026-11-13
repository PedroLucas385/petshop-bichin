
//Controller que realiza a ligação entre as camadas do Pet

package controller;

import model.Cliente;
import model.Pet;
import repository.ClienteRepository;
import repository.PetRepository;

import java.time.LocalDate;
import java.util.ArrayList;

public class PetController {
    private PetRepository repository;
    private ClienteRepository repositoryCliente;

    public PetController(PetRepository repository, ClienteRepository repositoryCliente) {
        this.repository = repository;
        this.repositoryCliente = repositoryCliente;
    }

    public ArrayList<Pet> listar(){

        return repository.listar();
    }

    public boolean remover(int id){
        return repository.remover(id);
    }

    public void adicionar(int id, String nome, String especie, String raca, LocalDate nascimento, int idCliente){
        Cliente cliente = this.repositoryCliente.buscarPorId(idCliente);
        if (cliente != null) {
            Pet pet = new Pet(id, nome, especie, raca, nascimento, cliente);
            this.repository.adicionar(pet);
        } else {
            System.out.println("Cliente com ID: " + idCliente + " não existe!");
        }
    }

    public Pet buscar(int id){
        return repository.buscarPorId(id);
    }


}
