
//Controller que realiza a ligação entre as camadas do Serviço

package controller;

import model.Servico;
import repository.ServicoRepository;

import java.util.ArrayList;

public class ServicoController {
    private ServicoRepository repository;

    public ServicoController(ServicoRepository repository) {
        this.repository = repository;
    }

    public boolean remover(int id){
        return repository.remover(id);
    }

    public void adicionar(int id, String descricao, double valor) {
        Servico servico = new Servico(id, descricao, valor);
        this.repository.adicionar(servico);
    }

    public ArrayList<Servico> listar(){
        return repository.listar();
    }

    public Servico buscar(int id){
        return repository.buscarPorId(id);
    }
}
