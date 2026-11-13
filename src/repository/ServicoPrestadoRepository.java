// Responsável pelo armazenamento e operações de persistência em memória dos serviços prestados
package repository;

import model.Cliente;
import model.Pet;
import model.Servico;
import model.ServicoPrestado;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;

public class ServicoPrestadoRepository {

    private ArrayList<ServicoPrestado> servicoPrestado;

    public ServicoPrestadoRepository(){
        servicoPrestado = new ArrayList<>();
    }

    public ArrayList<ServicoPrestado> listar() {
        return  this.servicoPrestado;
    }

    // Metodo responsável por carregar os dados(do servicos_prestados.txt) da camada de mesmo nome, com suas validações
    public void carregarDados(String caminhoArquivo, PetRepository repoPet, ServicoRepository repoServico) {
        java.time.format.DateTimeFormatter formato = java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy");

        try (java.io.BufferedReader br = new java.io.BufferedReader(new java.io.FileReader(caminhoArquivo))) {
            br.readLine();
            String linha;

            while ((linha = br.readLine()) != null) {
                String[] partes = linha.split(";");

                int id = Integer.parseInt(partes[0].trim());
                int idPet = Integer.parseInt(partes[1].trim());
                int idServico = Integer.parseInt(partes[2].trim());
                java.time.LocalDate data = java.time.LocalDate.parse(partes[3].trim(), formato);
                double valorCobrado = Double.parseDouble(partes[4].trim());

                Pet pet = repoPet.buscarPorId(idPet);
                Servico servico = repoServico.buscarPorId(idServico);

                if (pet != null && servico != null) {
                    ServicoPrestado sp = new ServicoPrestado(id, pet, servico, valorCobrado, data);
                    this.adicionar(sp);
                } else {
                    System.out.println("Erro: Pet ou Servico não encontrado na linha: " + linha);
                }
            }
        } catch (Exception e) {
            System.out.println("Erro ao carregar serviços prestados: " + e.getMessage());
        }
    }

    // Metodo que busca o periodo do serviço prestado usando filtro de datas com LocalDate
    public ArrayList<ServicoPrestado> buscarPorPeriodo(LocalDate inicio, LocalDate fim) {
        ArrayList<ServicoPrestado> periodo = new ArrayList<>();
        for (ServicoPrestado sp : this.servicoPrestado) {
            LocalDate dataAtendimento = sp.getData();
            if (!dataAtendimento.isBefore(inicio) && !dataAtendimento.isAfter(fim)) {
                periodo.add(sp);
            }
        }
        return periodo;
    }

    public boolean remover(int id) {
        for (ServicoPrestado sp : this.servicoPrestado) {
            if (sp.getId() == id) {
                servicoPrestado.remove(sp);
                return true;
            }
        }
        return false;
    }

    // Metodo que usca os pets atendidos a partir do nome do dono com remoção de duplicados
    public ArrayList<Pet> buscarBichosPorNomeDono(String nome) {
        ArrayList<Pet> pets = new ArrayList<>();
        for (ServicoPrestado sp : this.servicoPrestado) {
            if (Objects.equals(sp.getPet().getCliente().getNome(), nome)) {
                Pet animal = sp.getPet();
                if (!pets.contains(animal)) {
                    pets.add(animal);
                }
            }
        }
        return pets;
    }

    public ServicoPrestado buscarPorId(int id){
        for(ServicoPrestado sp : this.servicoPrestado){
            if(sp.getId() == id) {
                return sp;
            }
        }
        return null;
    }
    //Metodo pra busca de cliente
    public ArrayList<ServicoPrestado> buscarPorCliente(Cliente cliente) {
        ArrayList<ServicoPrestado> historicoCliente = new ArrayList<>();
        for (ServicoPrestado sp : this.servicoPrestado) {
            if (sp.getPet().getCliente().equals(cliente)) {
                historicoCliente.add(sp);
            }
        }
        return historicoCliente;
    }


    // Metodo pra obter o maior valor por cliente com algoritmo de busca por recorde
    public double maiorValorPorCliente(Cliente cliente) {
        double maior = 0.0;
        for(ServicoPrestado sp : this.servicoPrestado) {
            if (sp.getPet().getCliente().equals(cliente)) {
                double valoratendimento = sp.getvalorCobrado();
                if (valoratendimento > maior) {
                    maior = valoratendimento;
                }
            }
        }
        return maior;
    }

    public void adicionar(ServicoPrestado sp) {
        servicoPrestado.add(sp);
    }
}
