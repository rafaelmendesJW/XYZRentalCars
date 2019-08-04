package lab5;

import lab5.Util.MFactory;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public class Main {
    public static void main(String[] args) {
        EntityManager em = MFactory.getEntityManager();
    }
}
