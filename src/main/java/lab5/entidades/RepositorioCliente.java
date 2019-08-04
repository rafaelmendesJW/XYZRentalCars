package lab5.entidades;

import lab5.Util.MFactory;

import javax.persistence.EntityManager;

public class RepositorioCliente {
    EntityManager em;
    DaoGenerico <Cliente> dg;

    public RepositorioCliente(EntityManager em){
        this.em = em;
        this.dg = new DaoGenerico<>(em);
    }

    public Cliente busca(Integer id){
        return dg.busca(Cliente.class,id);
    }

    public Cliente salvarAtualizar(Cliente cliente){
        return dg.salvarAtualizar(cliente);
    }
    public void remove(Cliente cliente){
        dg.remove(cliente);
    }
}
