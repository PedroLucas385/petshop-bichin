// Responsável pelo armazenamento e operações de persistência em memória dos pets
package repository;

import model.Cliente;
import model.Pet;
import java.util.ArrayList;

public class PetRepository {
    private ArrayList<Pet> pets;

    public PetRepository(){
        this.pets = new ArrayList<>();
    }

    public boolean remover(int id) {
        for (Pet p : this.pets) {
            if (p.getId() == id){
                pets.remove(p);
                return true;
            }
        }
        return false;
    }

    public ArrayList<Pet> listar(){
        return this.pets;
    }

    public void adicionar(Pet p) {
        pets.add(p);
    }

    // Metodo responsável por carregar os dados(pelo pets.txt) da camada de mesmo nome, com suas validações
    public void carregaDados(String caminhoArquivo, ClienteRepository repoCliente) {
        java.time.format.DateTimeFormatter formato = java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy");

        try (java.io.BufferedReader br = new java.io.BufferedReader(new java.io.FileReader(caminhoArquivo))) {
            br.readLine();
            String linha;

            while ((linha = br.readLine()) != null) {
                String[] partes = linha.split(";");

                int id = Integer.parseInt(partes[0].trim());
                String nome = partes[1].trim();
                String especie = partes[2].trim();
                String raca = partes[3].trim();
                java.time.LocalDate dataNasc = java.time.LocalDate.parse(partes[4].trim(), formato);
                int idCliente = Integer.parseInt(partes[5].trim());

                Cliente dono = repoCliente.buscarPorId(idCliente);

                if (dono != null) {
                    Pet pet = new Pet(id, nome, especie, raca, dataNasc, dono);
                    this.adicionar(pet);
                } else {
                    System.out.println("Cliente com id " + idCliente + " não foi encontrado para o pet " + nome);
                }
            }
        } catch (Exception e) {
            System.out.println("Erro ao carregar pets: " + e.getMessage());
        }
    }

    public Pet buscarPorId(int id) {
        for (Pet p : this.pets) {
            if (p.getId() == id) {
                return p;
            }
        }
        return null;
    }
}
