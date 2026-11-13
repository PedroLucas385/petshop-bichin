package visao.cmd;


import java.util.Scanner;

    public class UICmd {
        private UiCmdServico uiCmdServico;
        private UiCmdPet uiCmdPet;
        private Scanner teclado;
        private UiCmdCliente uiCmdCliente;
        private UiCmdServicoPrestado menuServicoPrestado;

        public UICmd(UiCmdCliente uiCmdCliente, UiCmdPet uiCmdPet, UiCmdServico uiCmdServico, UiCmdServicoPrestado menuServicoPrestado) {
            this.teclado = new Scanner(System.in);
            this.uiCmdCliente = uiCmdCliente;
            this.uiCmdPet = uiCmdPet;
            this.uiCmdServico = uiCmdServico;
            this.menuServicoPrestado = menuServicoPrestado;
        }

        public void iniciar() {
            int opcao = -1;

            do {
                System.out.println("\n=========================");
                System.out.println("        PET SHOP         ");
                System.out.println("=========================");
                System.out.println("1 - Clientes");
                System.out.println("2 - Pets");
                System.out.println("3 - Serviços");
                System.out.println("4 - Serviços Prestados");
                System.out.println("0 - Sair");
                System.out.print("Escolha uma opção: ");

                opcao = teclado.nextInt();
                teclado.nextLine();

                switch (opcao) {
                    case 1:
                        uiCmdCliente.iniciar();
                        break;
                    case 2:
                        uiCmdPet.iniciar();
                        break;
                    case 3:
                        uiCmdServico.iniciar();
                        break;
                    case 4:
                        menuServicoPrestado.iniciar();
                        break;
                    case 0:
                        System.out.println("Encerrando o sistema...");
                        break;
                    default:
                        System.out.println("Opção inválida! Tente novamente.");
                }
            } while (opcao != 0);
        }
    }

