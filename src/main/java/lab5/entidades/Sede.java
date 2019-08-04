package lab5.entidades;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.*;

@Entity
public class Sede implements EntidadeBase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nome;
    private String endereco;
    private String telefone;
    @Column (name = "nome_gerente")
    private String nomeDoGerente;
    @Column (name = "multa_outra_sede")
    private BigDecimal multaPorEntregaEmOutroPt;
    @OneToMany (mappedBy = "sedeDeLocacao" )
    private Set<Reserva> sedesDeLocacao = new HashSet<>();
    @OneToMany (mappedBy = "sedeDeDevolucao")
    private Set<Reserva> sedesDeDevolucao = new HashSet<>();
    @OneToMany (mappedBy = "sedeOrigem")
    private Set<Carro> carrosOrigem = new HashSet<>();
    @OneToMany (mappedBy = "localizacaoAtual")
    private Set<Carro> carrosLocalizacaoAtual = new HashSet<>();

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getNomeDoGerente() {
        return nomeDoGerente;
    }

    public void setNomeDoGerente(String nomeDoGerente) {
        this.nomeDoGerente = nomeDoGerente;
    }

    public BigDecimal getMultaPorEntregaEmOutroPt() {
        return multaPorEntregaEmOutroPt;
    }

    public void setMultaPorEntregaEmOutroPt(BigDecimal multaPorEntregaEmOutroPt) {
        this.multaPorEntregaEmOutroPt = multaPorEntregaEmOutroPt;
    }
    public List<Reserva> recuperarReservasNaofinalizadas (){
        List <Reserva> reservas = new LinkedList<>();
        for (Reserva r : sedesDeLocacao) {
            if(!r.getSituacaoReserva().equals(SituacaoReserva.finalizada)){
                reservas.add(r);
        }
    }
        return reservas;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sede sede = (Sede) o;
        return id.equals(sede.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public Integer getId() {
        return id;
    }
}
