package repository;

import model.Servico;

import java.util.ArrayList;

public class ServicoRepository {
    private ArrayList<Servico> servicos;

    public ServicoRepository() {
        this.servicos = new ArrayList<>();
    }

    public Servico buscarPorId(int id) {
        for (Servico s : this.servicos) {
            if (s.getId() == id) {
                return s;
            }
        }
        return null;
    }

    public ArrayList<Servico> listar() {
        return this.servicos;
    }

    public void adicionar(Servico servico) {
        servicos.add(servico);
    }

    public boolean remover(int id) {
        for (Servico s : this.servicos) {
            if (s.getId() == id) {
                servicos.remove(s);
                return true;
            }
        }
        return false;
    }

    // Metodo responsável por carregar os dados(do servicos.txt) da camada de mesmo nome, com suas validações
    public void carregarDados(String caminhoArquivo) {
        try (java.io.BufferedReader br = new java.io.BufferedReader(new java.io.FileReader(caminhoArquivo))) {
            br.readLine();
            String linha;

            while ((linha = br.readLine()) != null) {
                String[] partes = linha.split(";");

                int id = Integer.parseInt(partes[0].trim());
                String descricao = partes[1].trim();
                double valor= Double.parseDouble(partes[2].trim());
            }
        } catch (Exception e) {
            System.out.println("Erro ao carregar serviços prestados: " + e.getMessage());
        }
    }
}
