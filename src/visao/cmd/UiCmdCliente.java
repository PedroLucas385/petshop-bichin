// View do cliente por linha de comando
package visao.cmd;

import controller.ClienteController;
import model.Cliente;

import java.util.ArrayList;
import java.util.Scanner;

public class UiCmdCliente {
    private ClienteController controller;
    private Scanner teclado;

    public UiCmdCliente(ClienteController controller){
        teclado = new Scanner(System.in);
        this.controller = controller;

    }

    private void buscar() {
        System.out.println("=== BUSCAR CLIENTES ===");
        System.out.println("Digite o ID do cliente procurado: ");
        int id = teclado.nextInt();
        teclado.nextLine();

        Cliente c = this.controller.buscar(id);
        if (c != null) {
            System.out.println("Cliente encontrado: " + c);
        } else {
            System.out.println("Cliente não foi encontrado.");
        }
    }

    private void listar() {
        System.out.println("=== LISTA DE CLIENTES ===");
        ArrayList<Cliente> lista = this.controller.listar();
        if (lista.isEmpty()) {
            System.out.println("Não há clientes cadastrados");
        } else {
            for (Cliente c : lista) {
                System.out.println(c);
            }
        }
    }

    public void iniciar() {
        int opcao = -1;

        do {
            System.out.println("=== GERENCIAR CLIENTES ===");
            System.out.println("1 - Buscar clientes por ID");
            System.out.println("2 - Listar clientes");
            System.out.println("3 - Alterar clientes");
            System.out.println("4 - Remover clientes");
            System.out.println("0 - Sair para o Menu");
            System.out.println("Escolha uma opção: ");
            opcao = teclado.nextInt();

            switch (opcao) {
                case 1:
                    buscar();
                    break;
                case 2:
                    listar();
                    break;
                case 3:
                    alterar();
                    break;
                case 4:
                    apagar();
                    break;
                case 0:
                    System.out.println("Voltando ao menu principal!");
                default:
                    System.out.println("Opção inválida! Escolha um dos números");
            }
        } while (opcao != 0);
    }
        private void alterar() {
            System.out.println("=== CLIENTES A ALTERAR ===");
            System.out.println("Digite o ID do cliente a ser alterado: ");
            int id = teclado.nextInt();
            teclado.nextLine();

            Cliente cliente = this.controller.buscar(id);

            if (cliente != null) {
                System.out.println("Os dados do cliente são: " + cliente);

                System.out.println("Digite o novo nome: ");
                String novoNome = teclado.nextLine();

                System.out.println("Digite o novo telefone: ");
                String novoTelefone = teclado.nextLine();

                cliente.setNome(novoNome);
                cliente.setTelefone(novoTelefone);

            } else {
                System.out.println("Cliente não encontrado!");
            }

        }

    private void apagar() {
        System.out.println("=== APAGAR CLIENTE ===");
        System.out.println("Digite o ID do cliente a ser removido");
        int id = teclado.nextInt();
        teclado.nextLine();

        boolean removeu = this.controller.remover(id);

        if (removeu) {
            System.out.println("Cliente completamente removido!");
        } else {
            System.out.println("Cliente não foi possível de ser removido(ID ausente)");
        }
    }
}
