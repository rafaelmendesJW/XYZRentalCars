package lab5.entidades;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
public class Carro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String placa;
    private String modelo;
    private Short ano;
    private BigDecimal quilometragem;
    private String descricao;
    @ManyToOne
    @JoinColumn (name = "id_classe", nullable = true)
    private ClassesDeCarro classe;
    @ManyToOne
    @JoinColumn (name = "id_sede_origem", nullable = false)
    private Sede sedeOrigem;
    @ManyToOne
    @JoinColumn (name = "id_localizacao_atual", nullable = true)
    private Sede localizacaoAtual;
    @Enumerated (EnumType.STRING)
    private SituacaoCarro situacao;

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public Short getAno() {
        return ano;
    }

    public void setAno(Short ano) {
        this.ano = ano;
    }

    public BigDecimal getQuilometragem() {
        return quilometragem;
    }

    public void setQuilometragem(BigDecimal quilometragem) {
        this.quilometragem = quilometragem;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Carro carro = (Carro) o;
        return id.equals(carro.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
