// View do servico em linha de comando
package visao.cmd;

import controller.ServicoController;
import model.Servico;

import java.util.ArrayList;
import java.util.Scanner;

public class UiCmdServico {
    private Scanner teclado;
    private ServicoController controller;

    public UiCmdServico(ServicoController controller) {
        teclado = new Scanner(System.in);
        this.controller = controller;
    }

    public void iniciar() {
        int opcao = -1;

        do {
            System.out.println("=== GERENCIAR SERVIÇOS ===");
            System.out.println("1 - Cadastrar serviço");
            System.out.println("2 - Listar serviços");
            System.out.println("3 - Buscar serviço por ID");
            System.out.println("4 - Alterar serviço");
            System.out.println("5 - Remover serviço");
            System.out.println("0 - Sair para o Menu");
            System.out.println("Escolha uma opção: ");
            opcao = teclado.nextInt();

            switch (opcao) {
                case 1:
                    adicionar();
                    break;
                case 2:
                    listar();
                    break;
                case 3:
                    buscar();
                    break;
                case 4:
                    alterar();
                    break;
                case 5:
                    apagar();
                    break;
                case 0:
                    System.out.println("Voltando ao menu principal!");
                    break;
                default:
                    System.out.println("Opção inválida! Escolha um dos números");
            }
        } while (opcao != 0);
    }

    private void adicionar() {
        System.out.println("=== ADIÇÃO DE SERVIÇOS ===");

        System.out.println("Digite o ID: ");
        int id = teclado.nextInt();
        teclado.nextLine();

        System.out.println("Digite a descrição do serviço: ");
        String descricao = teclado.nextLine();

        System.out.println("Digite o valor: ");
        double valor = teclado.nextDouble();
        teclado.nextLine();

        this.controller.adicionar(id, descricao, valor);
    }

    private void listar() {
        System.out.println("=== LISTA DE SERVIÇOS ===");
        ArrayList<Servico> lista = this.controller.listar();
        if (lista.isEmpty()) {
            System.out.println("Não há serviços cadastrados");
        } else {
            for (Servico s : lista) {
                System.out.println(s);
            }
        }
    }

    private void alterar() {
        System.out.println("=== SERVIÇOS A ALTERAR ===");
        System.out.println("Digite o ID do serviço a ser alterado: ");
        int id = teclado.nextInt();
        teclado.nextLine();

        Servico servico = this.controller.buscar(id);

        if (servico != null) {
            System.out.println("Os dados do serviço são: " + servico);

            System.out.println("Digite a nova descrição: ");
            String novaDescricao = teclado.nextLine();

            System.out.println("Digite o novo valor: ");
            double novoValor = teclado.nextDouble();
            teclado.nextLine();

            servico.setDescricao(novaDescricao);
            servico.setValor(novoValor);
            System.out.println("Serviço alterado com sucesso!");
        } else {
            System.out.println("Serviço não encontrado!");
        }
    }

    private void buscar() {
        System.out.println("=== BUSCAR SERVIÇOS ===");
        System.out.println("Digite o ID do serviço procurado: ");
        int id = teclado.nextInt();
        teclado.nextLine();

        Servico s = this.controller.buscar(id);
        if (s != null) {
            System.out.println("Serviço encontrado: " + s);
        } else {
            System.out.println("Serviço não foi encontrado.");
        }
    }

    private void apagar() {
        System.out.println("=== REMOVER SERVIÇO ===");
        System.out.println("Digite o ID do serviço a ser removido");
        int id = teclado.nextInt();
        teclado.nextLine();

        boolean removeu = this.controller.remover(id);

        if (removeu) {
            System.out.println("Serviço completamente removido!");
        } else {
            System.out.println("Serviço não foi possível de ser removido(ID ausente)");
        }
    }
}