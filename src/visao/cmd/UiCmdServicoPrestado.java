// View do servico prestado por linha de comando
package visao.cmd;

import controller.ClienteController;
import controller.ServicoPrestadoController;
import model.Cliente;
import model.Pet;
import model.ServicoPrestado;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class UiCmdServicoPrestado {
    private Scanner teclado;
    private ClienteController controllerCliente;
    private ServicoPrestadoController controller;

    public UiCmdServicoPrestado(ServicoPrestadoController controller, ClienteController controllerCliente) {
        this.teclado = new Scanner(System.in);
        this.controller = controller;
        this.controllerCliente = controllerCliente;
    }

    public void iniciar() {
        int opcao = -1;

        do {
            System.out.println("=== GERENCIAR SERVIÇOS PRESTADOS ===");
            System.out.println("1 - Adicionar atendimento");
            System.out.println("2 - Listar atendimentos");
            System.out.println("3 - Buscar atendimento por ID");
            System.out.println("4 - Apagar atendimento");
            System.out.println("5 - Serviços por período");
            System.out.println("6 - Animais do dono");
            System.out.println("7 - Serviços do cliente");
            System.out.println("8 - Maior valor gasto por cliente");
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
                    apagar();
                    break;
                case 5:
                    servicosPorPeriodo();
                    break;
                case 6:
                    animaisDoDono();
                    break;
                case 7:
                    servicosDoCliente();
                    break;
                case 8:
                    maiorValor();
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
        System.out.println("=== ADIÇÃO DE SERVIÇO PRESTADO ===");

        System.out.println("Digite o ID: ");
        int id = teclado.nextInt();
        teclado.nextLine();

        System.out.println("Digite o ID do pet atendido: ");
        int idPet = teclado.nextInt();
        teclado.nextLine();

        System.out.println("Digite o ID do serviço: ");
        int idServico = teclado.nextInt();
        teclado.nextLine();

        System.out.println("Digite o valor cobrado: ");
        double valor = teclado.nextDouble();
        teclado.nextLine();

        System.out.println("Digite a data do atendimento (AAAA-MM-DD): ");
        String dataStr = teclado.nextLine();
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate data = LocalDate.parse(dataStr, formato);
        this.controller.adicionar(id, idPet, idServico, valor, data);
        System.out.println("Serviço prestado cadastrado com sucesso!");
    }

    private void listar() {
        System.out.println("=== LISTA DE SERVIÇOS PRESTADOS ===");
        ArrayList<ServicoPrestado> lista = this.controller.listar();
        if (lista.isEmpty()) {
            System.out.println("Não há atendimentos cadastrados");
        } else {
            for (ServicoPrestado sp : lista) {
                System.out.println(sp);
            }
        }
    }

    private void buscar() {
        System.out.println("=== BUSCAR SERVIÇO PRESTADO ===");
        System.out.println("Digite o ID do serviço prestado: ");
        int id = teclado.nextInt();
        teclado.nextLine();

        ServicoPrestado sp = this.controller.buscar(id);
        if (sp != null) {
            System.out.println("Atendimento encontrado: " + sp);
        } else {
            System.out.println("Atendimento não foi encontrado.");
        }
    }

    private void apagar() {
        System.out.println("=== REMOVER SERVIÇO PRESTADO ===");
        System.out.println("Digite o ID do atendimento a ser removido: ");
        int id = teclado.nextInt();
        teclado.nextLine();

        boolean removeu = this.controller.remover(id);

        if (removeu) {
            System.out.println("Atendimento completamente removido!");
        } else {
            System.out.println("Não foi possível remover o atendimento (ID ausente)");
        }
    }

    // Filtra atendimentos realizados dentro de um intervalo de datas
    private void servicosPorPeriodo() {
        System.out.println("=== SERVIÇOS POR PERÍODO ===");

        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        System.out.print("Digite a data inicial (dd/MM/yyyy): ");
        String inicioStr = teclado.nextLine();
        LocalDate inicio = LocalDate.parse(inicioStr, formato);

        System.out.print("Digite a data final (dd/MM/yyyy): ");
        String fimStr = teclado.nextLine();
        LocalDate fim = LocalDate.parse(fimStr, formato);

        ArrayList<ServicoPrestado> lista = this.controller.buscarPorPeriodo(inicio, fim);
        if (lista.isEmpty()) {
            System.out.println("Nenhum serviço prestado encontrado nesse período.");
        } else {
            for (ServicoPrestado sp : lista) {
                System.out.println(sp);
            }
        }
    }

    // Consulta os pets atendidos filtrando pelo nome do tutor
    private void animaisDoDono() {
        System.out.println("=== ANIMAIS DO DONO ===");
        System.out.println("Digite o nome do dono: ");
        String nome = teclado.nextLine();

        ArrayList<Pet> lista = this.controller.buscarBichosPorNomeDono(nome);
        if (lista.isEmpty()) {
            System.out.println("Nenhum pet encontrado para esse dono.");
        } else {
            for (Pet p : lista) {
                System.out.println(p);
            }
        }
    }

    // Lista o historico completo de atendimentos vinculados a um cliente
    private void servicosDoCliente() {
        System.out.println("=== SERVIÇOS DO CLIENTE ===");
        System.out.println("Digite o ID do cliente: ");
        int idCliente = teclado.nextInt();
        teclado.nextLine();

        Cliente c = this.controllerCliente.buscar(idCliente);
        if (c != null) {
            ArrayList<ServicoPrestado> lista = this.controller.buscarPorCliente(c);
            if (lista.isEmpty()) {
                System.out.println("Nenhum serviço prestado encontrado para este cliente.");
            } else {
                for (ServicoPrestado sp : lista) {
                    System.out.println(sp);
                }
            }
        } else {
            System.out.println("Cliente não encontrado.");
        }
    }

    // Exibe o atendimento individual de valor mais alto registrado para o cliente
    private void maiorValor() {
        System.out.println("=== MAIOR VALOR POR CLIENTE ===");
        System.out.println("Digite o ID do cliente: ");
        int idCliente = teclado.nextInt();
        teclado.nextLine();

        Cliente c = this.controllerCliente.buscar(idCliente);
        if (c != null) {
            double maior = this.controller.maiorValorPorCliente(c);
            System.out.println("Maior valor gasto pelo cliente: " + maior);
        } else {
            System.out.println("Cliente não encontrado.");
        }
    }
}