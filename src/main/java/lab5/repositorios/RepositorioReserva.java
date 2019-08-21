package lab5.repositorios;

import lab5.Util.MFactory;
import lab5.entidades.Reserva;

import javax.persistence.EntityManager;

public class RepositorioReserva {
    EntityManager em = MFactory.getEntityManager();
    DaoGenerico<Reserva> dg;
    public RepositorioReserva(EntityManager em) {
        this.em = em;
        dg = new DaoGenerico <Reserva> (em);
    }

    public Reserva busca(Integer id){
        return dg.busca(Reserva.class,id);
    }

    public Reserva salvarAtualizar(Reserva reserva){
        return dg.salvarAtualizar(reserva);
    }
    public void remove(Reserva reserva){
        dg.remove(reserva);
    }
}
