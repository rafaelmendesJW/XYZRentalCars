package lab5.entidades;

import lab5.Util.MFactory;

import javax.persistence.EntityManager;

public class RepositorioSede {
    EntityManager em = MFactory.getEntityManager();
    DaoGenerico <Sede> dg;
    public RepositorioSede(EntityManager em) {
        this.em = em;
        dg = new DaoGenerico <Sede> (em);
    }

    public Sede busca (Integer id){
        return dg.busca(Sede.class,id);
    }
    public Sede salvarAtualizar(Sede sede){
        return dg.salvarAtualizar(sede);
    }
    public void remove(Sede sede){
        dg.remove(sede);
    }
}
