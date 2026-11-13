import controller.ClienteController;
import controller.PetController;
import controller.ServicoController;
import controller.ServicoPrestadoController;
import repository.ClienteRepository;
import repository.PetRepository;
import repository.ServicoPrestadoRepository;
import repository.ServicoRepository;
import visao.cmd.*;

public class Main {

    public static void main(String[] args) {

        // 1. Repositorios
        ClienteRepository clienteRepository = new ClienteRepository();
        ServicoRepository servicoRepository = new ServicoRepository();
        PetRepository petRepository = new PetRepository();
        ServicoPrestadoRepository servicoPrestadoRepository = new ServicoPrestadoRepository();

        // 2. Carga inicial dos arquivos
        clienteRepository.carregarDados("dados/clientes.txt");
        servicoRepository.carregarDados("dados/servicos.txt");
        petRepository.carregaDados("dados/pets.txt", clienteRepository);
        servicoPrestadoRepository.carregarDados("dados/servicos_prestados.txt", petRepository, servicoRepository);

        // 3. Controllers
        ClienteController clienteController = new ClienteController(clienteRepository);
        ServicoController servicoController = new ServicoController(servicoRepository);
        PetController petController = new PetController(petRepository, clienteRepository);
        ServicoPrestadoController servicoPrestadoController = new ServicoPrestadoController(servicoPrestadoRepository, petRepository, servicoRepository);

        // 4. Telas (Views)
        UiCmdCliente uiCmdCliente = new UiCmdCliente(clienteController);
        UiCmdServico uiCmdServico = new UiCmdServico(servicoController);
        UiCmdPet uiCmdPet = new UiCmdPet(petController, clienteController);
        UiCmdServicoPrestado menuServicoPrestado = new UiCmdServicoPrestado(servicoPrestadoController, clienteController);

        // 5. Menu Principal
        UICmd UICmd = new UICmd(
                uiCmdCliente,
                uiCmdPet,
                uiCmdServico,
                menuServicoPrestado
        );

        UICmd.iniciar();
    }
}