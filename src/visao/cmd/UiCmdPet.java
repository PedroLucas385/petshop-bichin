//View do pet em linha de comando
package visao.cmd;

import controller.ClienteController;
import controller.PetController;
import model.Cliente;
import model.Pet;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class UiCmdPet {
    private Scanner teclado;
    private ClienteController controllerCliente;
    private PetController controller;

    public UiCmdPet(PetController controller, ClienteController controllerCliente){
        teclado = new Scanner(System.in);

        this.controller = controller;
        this.controllerCliente = controllerCliente;
    }

    public void iniciar(){
        int opcao = -1;

        do {
            System.out.println("=== GERENCIAR PETS ===");
            System.out.println("1 - Cadastrar pets");
            System.out.println("2 - Listar pets");
            System.out.println("3 - Buscar pet por ID");
            System.out.println("4 - Alterar pet");
            System.out.println("5 - Remover pet");
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
                    remover();
                    break;
                case 0:
                    System.out.println("Voltando ao menu principal!");
                default:
                    System.out.println("Opção inválida! Escolha um dos números");
            }
        } while (opcao != 0);

    }

    private void adicionar() {
        System.out.println("=== ADIÇÃO DE PETS ===");

        System.out.println("Digite o ID: ");
        int id = teclado.nextInt();
        teclado.nextLine();

        System.out.println("Digite o nome do pet: ");
        String nome = teclado.nextLine();

        System.out.println("Digite o nome de sua espécie: ");
        String especie = teclado.nextLine();

        System.out.println("Digite a raça: ");
        String raca = teclado.nextLine();

        System.out.println("Digite a data de nascimento: ");
        String dataStr = teclado.nextLine();
        LocalDate nascimento = LocalDate.parse(dataStr);
        teclado.nextLine();

        System.out.println("Digite o ID de seu dono: ");
        int idDono = teclado.nextInt();
        teclado.nextLine();

        this.controller.adicionar(id, nome, especie, raca, nascimento, idDono);
    }

    private void listar() {
        System.out.println("=== LISTA DE PETS ===");
        ArrayList<Pet> lista = this.controller.listar();
        if (lista.isEmpty()) {
            System.out.println("Não há pets cadastrados");
        } else {
            for (Pet p : lista) {
                System.out.println(p);
            }
        }
    }

    private void alterar() {
        System.out.println("=== PETS A ALTERAR ===");
        System.out.println("Digite o ID do pet a ser alterado: ");
        int id = teclado.nextInt();
        teclado.nextLine();

        Pet pet = this.controller.buscar(id);

        if (pet != null) {
            System.out.println("Os dados do pet são: " + pet);

            System.out.println("Digite o novo nome: ");
            String novoNome = teclado.nextLine();

            System.out.println("Digite a nova espécie:  ");
            String novaEsp = teclado.nextLine();

            System.out.println("Digite a nova raça: ");
            String novaRaca = teclado.nextLine();

            System.out.println("Digite a nova data de nascimento: ");
            String dataStr = teclado.nextLine();
            LocalDate novaData = LocalDate.parse(dataStr);

            System.out.println("Digite o novo ID do dono: ");
            int novoIdDono = teclado.nextInt();

            pet.setNome(novoNome);
            pet.setEspecie(novaEsp);
            pet.setRaca(novaRaca);
            Cliente novoDono = this.controllerCliente.buscar(novoIdDono);
            pet.setCliente(novoDono);
            pet.setNascimento(novaData);
        } else {
            System.out.println("Pet não encontrado!");
        }

    }

    private void buscar() {
    System.out.println("=== BUSCAR PETS ===");
        System.out.println("Digite o ID do pet procurado: ");
        int id = teclado.nextInt();
        teclado.nextLine();

        Pet p = this.controller.buscar(id);
        if (p != null) {
            System.out.println("Pet encontrado: " + p);
        } else {
            System.out.println("Pet não foi encontrado.");
        }
    }

    private void remover() {
        System.out.println("=== REMOVER PET ===");
        System.out.println("Digite o ID do pet a ser removido");
        int id = teclado.nextInt();
        teclado.nextLine();

        boolean removeu = this.controller.remover(id);

        if (removeu) {
            System.out.println("Pet completamente removido!");
        } else {
            System.out.println("Pet não foi possível de ser removido(ID ausente)");
        }
    }
}
