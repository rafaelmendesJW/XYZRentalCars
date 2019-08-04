package lab5.entidades;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
public class Carro implements EntidadeBase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String placa;
    private String modelo;
    private Short ano;
    private BigDecimal quilometragem;
    private String descricao;
    @Enumerated (EnumType.STRING)
    private ClassesDeCarro classe;
    @ManyToOne
    @JoinColumn (name = "id_sede_origem", nullable = false)
    private Sede sedeOrigem;
    @ManyToOne
    @JoinColumn (name = "id_localizacao_atual", nullable = true)
    private Sede localizacaoAtual;
    @Enumerated (EnumType.STRING)
    private SituacaoCarro situacao;
    private BigDecimal valorDiarias;

    public BigDecimal getValorDiarias() {
        return valorDiarias;
    }

    public void setValorDiarias(BigDecimal valorDiarias) {
        this.valorDiarias = valorDiarias;
    }

    public String getPlaca() {
        return placa;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public void setAno(Short ano) {
        this.ano = ano;
    }

    public ClassesDeCarro getClasse() {
        return classe;
    }

    public void setClasse(ClassesDeCarro classe) {
        this.classe = classe;
    }

    public Sede getSedeOrigem() {
        return sedeOrigem;
    }

    public void setSedeOrigem(Sede sedeOrigem) {
        this.sedeOrigem = sedeOrigem;
    }

    public Sede getLocalizacaoAtual() {
        return localizacaoAtual;
    }

    public void setLocalizacaoAtual(Sede localizacaoAtual) {
        this.localizacaoAtual = localizacaoAtual;
    }

    public SituacaoCarro getSituacao() {
        return situacao;
    }

    public void setSituacao(SituacaoCarro situacao) {
        this.situacao = situacao;
    }

    /*public void setPlaca(String placa) {
        this.placa = placa;
    }*/

    public String getModelo() {
        return modelo;
    }

    /*public void setModelo(String modelo) {
        this.modelo = modelo;
    }*/

    public Short getAno() {
        return ano;
    }
    /*
    public void setAno(Short ano) {
        this.ano = ano;
    }*/

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

    public void disponibilizar() {
        situacao =  situacao.disponibilizar();
    }

    public void alugar() {
        situacao =  situacao.alugar();
    }
    public void notificarForaPtOrigem() {
        situacao =  situacao.notificarForaPtOrigem();
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

    @Override
    public Integer getId() {
        return id;
    }
}
