package lab5.entidades;

import lab5.Util.MFactory;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class RepositorioCarro {
    EntityManager em = MFactory.getEntityManager();
    DaoGenerico <Carro> dg;

    public RepositorioCarro(EntityManager em) {
        this.em = em;
        dg = new DaoGenerico <Carro> (em);
    }
    public Carro busca(Integer id){
        return dg.busca(Carro.class,id);
    }

    public Carro salvarAtualizar(Carro carro){
        return dg.salvarAtualizar(carro);
    }
    public void remove(Carro carro){
        dg.remove(carro);
    }
    public List<Carro> buscaPorClasse(ClassesDeCarro classe){
        Query q = em.createQuery("from Carro where classe = :classe");
        q.setParameter("classe",classe);
        return q.getResultList();


    }


}
