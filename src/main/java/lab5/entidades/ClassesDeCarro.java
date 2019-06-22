package lab5.entidades;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

@Entity @Table (name = "Classe_Carro")
public class ClassesDeCarro {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nome;
    @Column (name = "valor_diaria_")
    private BigDecimal valorDaDiaria;


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public BigDecimal getValorDaDiaria() {
        return valorDaDiaria;
    }

    public void setValorDaDiaria(BigDecimal valorDaDiaria) {
        this.valorDaDiaria = valorDaDiaria;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClassesDeCarro that = (ClassesDeCarro) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
