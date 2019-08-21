package lab5.repositorios;

import lab5.Util.MFactory;
import lab5.entidades.EntidadeBase;

import javax.persistence.EntityManager;

public class DaoGenerico<T extends EntidadeBase> {
    private EntityManager em2;

    public DaoGenerico(EntityManager em2) {
        this.em2 = em2;
    }

    public T busca(Class<T> classe,Integer id){
        return  em2.find(classe, id);
    }

    public T salvarAtualizar (T objeto){
        if( objeto.getId() != null){
            em2.merge(objeto);
        }else{
            em2.persist(objeto);
        }
        return objeto;

    }

    public void remove (T objeto){
        em2.remove((objeto));

    }
}
