// Controller que realiza a ligação entre as camadas do Serviço Prestado
package controller;

import model.Cliente;
import model.Pet;
import model.Servico;
import model.ServicoPrestado;
import repository.PetRepository;
import repository.ServicoPrestadoRepository;
import repository.ServicoRepository;

import java.time.LocalDate;
import java.util.ArrayList;

public class ServicoPrestadoController {
    private PetRepository repositoryPet;
    private ServicoRepository repositoryServico;
    private ServicoPrestadoRepository repository;

    // Construtor que recebe os repositorios por injecao de dependencia
    public ServicoPrestadoController(ServicoPrestadoRepository repository, PetRepository repositoryPet, ServicoRepository repositoryServico) {
        this.repository = repository;
        this.repositoryPet = repositoryPet;
        this.repositoryServico = repositoryServico;
    }

    public double maiorValorPorCliente(Cliente cliente) {
        return repository.maiorValorPorCliente(cliente);
    }

    public boolean remover(int id) {
        return repository.remover(id);
    }

    public ServicoPrestado buscar(int id) {
        return repository.buscarPorId(id);
    }

    public ArrayList<Pet> buscarBichosPorNomeDono(String nome) {
        return repository.buscarBichosPorNomeDono(nome);
    }

    public ArrayList<ServicoPrestado> buscarPorPeriodo(LocalDate inicio, LocalDate fim) {
        return repository.buscarPorPeriodo(inicio, fim);
    }

    public void adicionar(int id, int idPet, int idServico, double valor, LocalDate data) {
        Pet pet = this.repositoryPet.buscarPorId(idPet);
        Servico servico = this.repositoryServico.buscarPorId(idServico);

        if (pet != null && servico != null) {
            ServicoPrestado sp = new ServicoPrestado(id, pet, servico, valor, data);
            this.repository.adicionar(sp);
        } else {
            System.out.println("Erro: Pet ou Serviço não encontrado.");
        }
    }

    public ArrayList<ServicoPrestado> buscarPorCliente(Cliente cliente) {
        return repository.buscarPorCliente(cliente);
    }

    public ArrayList<ServicoPrestado> listar() {
        return repository.listar();
    }
}