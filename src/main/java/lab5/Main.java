package lab5;

import javax.persistence.Persistence;

public class Main{
    public static void main(String[] args) {
        //Coloca entre as aspas o nome da persistence unit
        //Se encontra no arquivo xml dentro da pasta meta-inf
        //Uma das primeiras linhas
        Persistence.createEntityManagerFactory("");
    }
}