// Responsável pelo armazenamento e operações de persistência em memória dos clientes
package repository;

import model.Cliente;

import java.util.ArrayList;

public class ClienteRepository {
    private ArrayList<Cliente> clientes;

    public ClienteRepository() {
        this.clientes = new ArrayList<>();
    }

    public void adicionar(Cliente cliente) {
        clientes.add(cliente);
    }

    public ArrayList<Cliente> listar() {
        return this.clientes;
    }

    public Cliente buscarPorId(int id) {
        for (Cliente c : this.clientes) {
            if (c.getId() == id) {
                return c;
            }
        }
        return null;
    }

    public boolean remover(int id) {
        for (Cliente c : this.clientes) {
            if (c.getId() == id) {
                clientes.remove(c);
                return true;
            }
        }
        return false;
    }

    // Metodo responsável por carregar os dados(pelo clientes.txt) da camada de mesmo nome, com suas validações
    public void carregarDados(String caminhoArquivo) {
        try (java.io.BufferedReader br = new java.io.BufferedReader(new java.io.FileReader(caminhoArquivo))) {
            br.readLine();
            String linha;

            while ((linha = br.readLine()) != null) {
                String[] partes = linha.split(";");

                int id = Integer.parseInt(partes[0].trim());
                String nome = partes[1].trim();
                String telefone = partes[2].trim();

                Cliente cliente = new Cliente(id, nome, telefone);
                this.adicionar(cliente);
            }
        } catch (Exception e) {
            System.out.println("Erro ao carregar clientes: " + e.getMessage());
        }
    }
}
