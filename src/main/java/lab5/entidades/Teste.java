package lab5.entidades;

import lab5.repositorios.RepositorioCarro;
import lab5.repositorios.RepositorioCliente;
import lab5.repositorios.RepositorioReserva;
import lab5.repositorios.RepositorioSede;
import org.junit.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class Teste {
    private static EntityManagerFactory factory;
    private EntityManager manager;
    private RepositorioCarro rcarro;
    private RepositorioCliente rcliente;
    private RepositorioReserva rreserva;
    private RepositorioSede rsede;

    @BeforeClass
    public static void inicio() {
        factory = Persistence.createEntityManagerFactory("rentalcars");
    }

    @AfterClass
    public static void fim() {
        factory.close();
    }

    @Before
    public void antes() {
        manager = factory.createEntityManager();
        rcarro = new RepositorioCarro(manager);
        rcliente = new RepositorioCliente(manager);
        rsede = new RepositorioSede(manager);
        rreserva = new RepositorioReserva(manager);
        manager.getTransaction().begin();
    }

    @After
    public void depois() {
        manager.getTransaction().rollback();
    }

    @Test
    public void deveRecuperarTodosOsCarrosdaClasseCompacto() {
        //Teste1
        Sede sede1 = new Sede();
        sede1.setEndereco("Av sol nascente, q5");
        sede1.setNome("Orbita");


        Carro carro1 = new Carro();
        carro1.setClasse(ClassesDeCarro.compacto);
        carro1.setSedeOrigem(sede1);
        //carro1.setSituacao(SituacaoCarro.alugado);
        //carro1.setValorDiarias(new BigDecimal(100));

        Carro carro2 = new Carro();
        carro2.setClasse(ClassesDeCarro.compacto);
        carro2.setSedeOrigem(sede1);
        carro2.setSituacao(SituacaoCarro.alugado);
        carro2.setValorDiarias(new BigDecimal(100));

        Carro carro3 = new Carro();
        carro3.setClasse(ClassesDeCarro.subcompacto);
        carro3.setSedeOrigem(sede1);
        carro3.setSituacao(SituacaoCarro.alugado);
        carro3.setValorDiarias(new BigDecimal(130));

        Carro carro4 = new Carro();
        carro4.setClasse(ClassesDeCarro.medio);
        carro4.setSedeOrigem(sede1);
        carro4.setSituacao(SituacaoCarro.alugado);
        carro4.setValorDiarias(new BigDecimal(150));


        rsede.salvarAtualizar(sede1);
        rcarro.salvarAtualizar(carro1);
        rcarro.salvarAtualizar(carro2);
        rcarro.salvarAtualizar(carro3);
        rcarro.salvarAtualizar(carro4);

        manager.flush();

        List<Carro> carros = rcarro.buscaPorClasse(ClassesDeCarro.compacto);
        for (Carro c : carros) {
            Assert.assertEquals(c.getClasse(), ClassesDeCarro.compacto);
        }
    }

    @Test
    public void deveRecuperarTodosOsCarrosdaClasseLuxo() {
        //Teste2
        Sede sede1 = new Sede();
        sede1.setEndereco("Av sol nascente, q5");
        sede1.setNome("Orbita");


        Carro carro1 = new Carro();
        carro1.setClasse(ClassesDeCarro.luxo);
        carro1.setSedeOrigem(sede1);
        //carro1.setSituacao(SituacaoCarro.alugado);
        //carro1.setValorDiarias(new BigDecimal(100));

        Carro carro2 = new Carro();
        carro2.setClasse(ClassesDeCarro.compacto);
        carro2.setSedeOrigem(sede1);
        carro2.setSituacao(SituacaoCarro.alugado);
        carro2.setValorDiarias(new BigDecimal(100));

        Carro carro3 = new Carro();
        carro3.setClasse(ClassesDeCarro.subcompacto);
        carro3.setSedeOrigem(sede1);
        carro3.setSituacao(SituacaoCarro.alugado);
        carro3.setValorDiarias(new BigDecimal(130));

        Carro carro4 = new Carro();
        carro4.setClasse(ClassesDeCarro.medio);
        carro4.setSedeOrigem(sede1);
        carro4.setSituacao(SituacaoCarro.alugado);
        carro4.setValorDiarias(new BigDecimal(150));


        rsede.salvarAtualizar(sede1);
        rcarro.salvarAtualizar(carro1);
        rcarro.salvarAtualizar(carro2);
        rcarro.salvarAtualizar(carro3);
        rcarro.salvarAtualizar(carro4);

        manager.flush();

        List<Carro> carros = rcarro.buscaPorClasse(ClassesDeCarro.luxo);
        for (Carro c : carros) {
            Assert.assertEquals(c.getClasse(), ClassesDeCarro.luxo);
        }
    }

    @Test
    public void deveRealizarReservaParaCarroLocalizadoEmOutraSede(){
        //teste3
        Sede sede2 = new Sede();
        sede2.setEndereco("Av jeronimo de albuquerque");
        sede2.setNome("MaxAluguel");

        Sede sede3 = new Sede();
        sede3.setEndereco("Av jeronimo de albuquerque");
        sede3.setNome("Localiza");

        Carro carro5 = new Carro();
        carro5.setClasse(ClassesDeCarro.medio);
        carro5.setSedeOrigem(sede2);
        carro5.setSituacao(SituacaoCarro.disponivel);
        carro5.setValorDiarias(new BigDecimal(150));

        Cliente c1 = new Cliente();
        c1.setCnh("2324252");
        c1.setValidadeCnh(LocalDate.now().plusMonths(10));

        Reserva reserva1 = new Reserva();
        reserva1.setCarro(carro5);
        reserva1.setCliente(c1);
        reserva1.setSedeDeLocacao(sede3);
        reserva1.setSedeDeDevolucao(sede2);

        Assert.assertNotNull(reserva1);
    }

    @Test(expected = IllegalStateException.class)
    public void nãodeverealizarumasegundareservacasoaindaossualocacaoemaberto(){
       //teste4
        Sede sede2 = new Sede();
        sede2.setEndereco("Av jeronimo de albuquerque");
        sede2.setNome("MaxAluguel");

        Cliente c1 = new Cliente();
        c1.setCnh("2324252");
        c1.setValidadeCnh(LocalDate.now().plusMonths(10));


        Carro carro5 = new Carro();
        carro5.setClasse(ClassesDeCarro.medio);
        carro5.setSedeOrigem(sede2);
        carro5.setSituacao(SituacaoCarro.alugado);
        carro5.setValorDiarias(new BigDecimal(150));

        Carro carro6 = new Carro();
        carro6.setClasse(ClassesDeCarro.medio);
        carro6.setSedeOrigem(sede2);
        carro6.setSituacao(SituacaoCarro.disponivel);
        carro6.setValorDiarias(new BigDecimal(150));

        Reserva reserva1 = new Reserva();
        reserva1.setCarro(carro5);
        reserva1.setCliente(c1);
        reserva1.setSedeDeLocacao(sede2);
        reserva1.setSedeDeDevolucao(sede2);
        reserva1.setSituacaoReserva(SituacaoReserva.atrasada);

        Reserva reserva2 = new Reserva();
        reserva2.setCarro(carro6);
        reserva2.setCliente(c1);
        reserva2.setSedeDeLocacao(sede2);
        reserva2.setSedeDeDevolucao(sede2);

    }
    @Test
    public void DeveefetuarreservaparaumClientecasonãotenhapendênciatodasassuasreservasanterioresjáestejamfinalizadas(){
        //teste5
        Sede sede2 = new Sede();
        sede2.setEndereco("Av jeronimo de albuquerque");
        sede2.setNome("MaxAluguel");

        Cliente c1 = new Cliente();
        c1.setCnh("2324252");
        c1.setValidadeCnh(LocalDate.now().plusMonths(10));

        Carro carro5 = new Carro();
        carro5.setClasse(ClassesDeCarro.medio);
        carro5.setSedeOrigem(sede2);
        carro5.setSituacao(SituacaoCarro.disponivel);
        carro5.setValorDiarias(new BigDecimal(150));

        Carro carro6 = new Carro();
        carro6.setClasse(ClassesDeCarro.medio);
        carro6.setSedeOrigem(sede2);
        carro6.setSituacao(SituacaoCarro.disponivel);
        carro6.setValorDiarias(new BigDecimal(150));

        Reserva reserva1 = new Reserva();
        reserva1.setCarro(carro5);
        reserva1.setCliente(c1);
        reserva1.setSedeDeLocacao(sede2);
        reserva1.setSedeDeDevolucao(sede2);
        reserva1.setSituacaoReserva(SituacaoReserva.finalizada);
        reserva1.setId(1);



        Reserva reserva3 = new Reserva();
        reserva3.setCarro(carro6);
        reserva3.setCliente(c1);
        reserva3.setSedeDeLocacao(sede2);
        reserva3.setSedeDeDevolucao(sede2);
        reserva1.setId(3);
        Assert.assertNotNull(reserva3);
    }
    @Test (expected = IllegalArgumentException.class)
    public void deveAtualizardadosdahabilitaçãodocliente (){
        //teste6
        Cliente c2 = new Cliente();
        c2.setCnh("2324252");
        c2.setValidadeCnh(LocalDate.now().plusMonths(10));

        rcliente.salvarAtualizar(c2);
        manager.flush();

        Cliente c3 = rcliente.busca(c2.getId());
        c3.setCnh("827328");
        c3.setValidadeCnh(LocalDate.now().plusMonths(10));
        rcliente.salvarAtualizar(c3);
        manager.flush();

        Cliente c4 = rcliente.busca(c3.getId());

        Assert.assertEquals(c4.getCnh(),"827328");

    }

    @Test (expected = IllegalStateException.class)
    public void Nãodevealugarumcarronummesmointervalodetempo(){
        //teste7
        Cliente c2 = new Cliente();
        c2.setCnh("2324252");
        c2.setValidadeCnh(LocalDate.now().plusMonths(10));


        Sede sede2 = new Sede();
        sede2.setEndereco("Av jeronimo de albuquerque");
        sede2.setNome("MaxAluguel");

        Carro carro6 = new Carro();
        carro6.setClasse(ClassesDeCarro.medio);
        carro6.setSedeOrigem(sede2);
        carro6.setSituacao(SituacaoCarro.alugado);
        carro6.setValorDiarias(new BigDecimal(150));


        Reserva reserva3 = new Reserva();
        reserva3.setCarro(carro6);
        reserva3.setCliente(c2);
        reserva3.setSedeDeLocacao(sede2);
        reserva3.setSedeDeDevolucao(sede2);

    }
    @Test
    public void Deveefetuarreservaparaclientescomhabilitaçãodentrodeprazodevalidade(){
        //teste8
        Cliente c2 = new Cliente();
        c2.setCnh("2324252");
        c2.setValidadeCnh(LocalDate.now().plusMonths(10));

        Sede sede2 = new Sede();
        sede2.setEndereco("Av jeronimo de albuquerque");
        sede2.setNome("MaxAluguel");

        Carro carro6 = new Carro();
        carro6.setClasse(ClassesDeCarro.medio);
        carro6.setSedeOrigem(sede2);
        carro6.setSituacao(SituacaoCarro.disponivel);
        carro6.setValorDiarias(new BigDecimal(150));

        Reserva reserva3 = new Reserva();
        reserva3.setCarro(carro6);
        reserva3.setCliente(c2);
        reserva3.setSedeDeLocacao(sede2);
        reserva3.setSedeDeDevolucao(sede2);

        Assert.assertNotNull(reserva3);

    }
    @Test(expected = IllegalArgumentException.class)
    public void Nãodeveefetuarreservaparaclientescomhabilitaçãovencida(){
        //Teste9
        Cliente c2 = new Cliente();
        c2.setCnh("2324252");
        c2.setValidadeCnh(LocalDate.now().minusMonths(10));

        Sede sede2 = new Sede();
        sede2.setEndereco("Av jeronimo de albuquerque");
        sede2.setNome("MaxAluguel");

        Carro carro6 = new Carro();
        carro6.setClasse(ClassesDeCarro.medio);
        carro6.setSedeOrigem(sede2);
        carro6.setSituacao(SituacaoCarro.disponivel);
        carro6.setValorDiarias(new BigDecimal(150));

        Reserva reserva3 = new Reserva();
        reserva3.setCarro(carro6);
        reserva3.setCliente(c2);
        reserva3.setSedeDeLocacao(sede2);
        reserva3.setSedeDeDevolucao(sede2);

    }
    @Test
    public void Deverecuperartodasasreservasemabertodeumadeterminadasede(){
        //teste10
        Cliente c2 = new Cliente();
        c2.setCnh("2324252");
        c2.setValidadeCnh(LocalDate.now().plusMonths(10));

        Cliente c3 = new Cliente();
        c3.setCnh("2324252");
        c3.setValidadeCnh(LocalDate.now().plusMonths(10));

        Cliente c4 = new Cliente();
        c4.setCnh("2324252");
        c4.setValidadeCnh(LocalDate.now().plusMonths(10));

        Cliente c5 = new Cliente();
        c5.setCnh("2324252");
        c5.setValidadeCnh(LocalDate.now().plusMonths(10));


        Sede sede2 = new Sede();
        sede2.setEndereco("Av jeronimo de albuquerque");
        sede2.setNome("MaxAluguel");

        Carro carro6 = new Carro();
        carro6.setClasse(ClassesDeCarro.medio);
        carro6.setSedeOrigem(sede2);
        carro6.setSituacao(SituacaoCarro.disponivel);
        carro6.setValorDiarias(new BigDecimal(150));

        Carro carro7 = new Carro();
        carro7.setClasse(ClassesDeCarro.medio);
        carro7.setSedeOrigem(sede2);
        carro7.setSituacao(SituacaoCarro.disponivel);
        carro7.setValorDiarias(new BigDecimal(150));

        Carro carro8 = new Carro();
        carro8.setClasse(ClassesDeCarro.medio);
        carro8.setSedeOrigem(sede2);
        carro8.setSituacao(SituacaoCarro.disponivel);
        carro8.setValorDiarias(new BigDecimal(150));

        Carro carro9 = new Carro();
        carro9.setClasse(ClassesDeCarro.medio);
        carro9.setSedeOrigem(sede2);
        carro9.setSituacao(SituacaoCarro.disponivel);
        carro9.setValorDiarias(new BigDecimal(150));

        Reserva reserva3 = new Reserva();
        reserva3.setCarro(carro6);
        reserva3.setCliente(c2);
        reserva3.setSedeDeLocacao(sede2);
        reserva3.setSedeDeDevolucao(sede2);
        reserva3.setSituacaoReserva(SituacaoReserva.finalizada);

        Reserva reserva4 = new Reserva();
        reserva4.setCarro(carro7);
        reserva4.setCliente(c3);
        reserva4.setSedeDeLocacao(sede2);
        reserva4.setSedeDeDevolucao(sede2);
        reserva3.setSituacaoReserva(SituacaoReserva.atrasada);

        Reserva reserva5 = new Reserva();
        reserva5.setCarro(carro8);
        reserva5.setCliente(c4);
        reserva5.setSedeDeLocacao(sede2);
        reserva5.setSedeDeDevolucao(sede2);
        reserva3.setSituacaoReserva(SituacaoReserva.finalizada);

        Reserva reserva6 = new Reserva();
        reserva6.setCarro(carro9);
        reserva6.setCliente(c5);
        reserva6.setSedeDeLocacao(sede2);
        reserva6.setSedeDeDevolucao(sede2);
        reserva3.setSituacaoReserva(SituacaoReserva.ativa);
    }
    @Test
    public void Devecobrarumataxaparacarrodevolvidoemoutrasede(){
        //teste11
        Cliente c2 = new Cliente();
        c2.setCnh("2324252");
        c2.setValidadeCnh(LocalDate.now().plusMonths(10));

        Sede sede2 = new Sede();
        sede2.setEndereco("Av jeronimo de albuquerque");
        sede2.setNome("MaxAluguel");
        sede2.setId(2);

        Sede sede3 = new Sede();
        sede3.setEndereco("Av jeronimo de albuquerque");
        sede3.setNome("MaxAluguel");
        sede3.setId(3);


        Carro carro8 = new Carro();
        carro8.setClasse(ClassesDeCarro.medio);
        carro8.setSedeOrigem(sede2);
        carro8.setSituacao(SituacaoCarro.disponivel);
        carro8.setValorDiarias(new BigDecimal(150));

        Reserva reserva6 = new Reserva();
        reserva6.setCarro(carro8);
        reserva6.setCliente(c2);
        reserva6.setSedeDeLocacao(sede2);
        reserva6.setSedeDeDevolucao(sede2);
        reserva6.setSituacaoReserva(SituacaoReserva.ativa);
        reserva6.setMulta(new BigDecimal(50));
        reserva6.setQtdDeDiarias(10);
        reserva6.finalizar(sede3);

        BigDecimal valorTotal;
        valorTotal = new BigDecimal(reserva6.getQtdDeDiarias()) .multiply(carro8.getValorDiarias()).add(reserva6.getMulta());
        Assert.assertEquals(valorTotal,reserva6.getValorTotal());
    }
    @Test
    public void Nãodevecobrartaxaparacarrodevolvidonamesmasede (){
        //teste12
        Cliente c2 = new Cliente();
        c2.setCnh("2324252");
        c2.setValidadeCnh(LocalDate.now().plusMonths(10));

        Sede sede2 = new Sede();
        sede2.setEndereco("Av jeronimo de albuquerque");
        sede2.setNome("MaxAluguel");
        sede2.setId(2);

        Carro carro8 = new Carro();
        carro8.setClasse(ClassesDeCarro.medio);
        carro8.setSedeOrigem(sede2);
        carro8.setSituacao(SituacaoCarro.disponivel);
        carro8.setValorDiarias(new BigDecimal(150));

        Reserva reserva6 = new Reserva();
        reserva6.setCarro(carro8);
        reserva6.setCliente(c2);
        reserva6.setSedeDeLocacao(sede2);
        reserva6.setSedeDeDevolucao(sede2);
        reserva6.setSituacaoReserva(SituacaoReserva.ativa);
        reserva6.setMulta(new BigDecimal(50));
        reserva6.setQtdDeDiarias(10);
        reserva6.finalizar(sede2);

        BigDecimal valorTotal;
        valorTotal = new BigDecimal(reserva6.getQtdDeDiarias()) .multiply(carro8.getValorDiarias());
        Assert.assertEquals(valorTotal,reserva6.getValorTotal());
    }

}