package lab5.entidades;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

@Entity
public class Reserva {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column (name = "numero_reserva")
    private String numReserva;
    @Column (name = "quantidade_diarias")
    private Integer qtdDeDiarias;
    @Column (name = "data_de_locacao")
    private LocalDate dataDeLocacao;
    @Column (name = "data_de_retorno")
    private LocalDate dataDeRetorno;
    @Column (name = "km_rodado")
    private BigDecimal kmRodado;
    private BigDecimal multa;
    @Column (name = "situacao_da_reserva")
    @Enumerated(EnumType.STRING)
    private SituacaoReserva situacaoReserva;
    @Column (name = "valor_total")
    private BigDecimal valorTotal;
    @ManyToOne
    @JoinColumn(name = "id_sede_locacao", nullable = false)
    private Sede sedeDeLocacao;
    @ManyToOne
    @JoinColumn( name = "id_sede_devolucao", nullable = false)
    private Sede sedeDeDevolucao;
    @ManyToOne
    @JoinColumn (name = "id_cliente", nullable = false)
    private Cliente cliente;
    @ManyToOne
    @JoinColumn (name = "id_carro", nullable = false)
    private Carro carro;


    public String getNumReserva() {
        return numReserva;
    }

    public void setNumReserva(String numReserva) {
        this.numReserva = numReserva;
    }

    public Integer getQtdDeDiarias() {
        return qtdDeDiarias;
    }

    public void setQtdDeDiarias(Integer qtdDeDiarias) {
        this.qtdDeDiarias = qtdDeDiarias;
    }

    public LocalDate getDataDeLocacao() {
        return dataDeLocacao;
    }

    public void setDataDeLocacao(LocalDate dataDeLocacao) {
        this.dataDeLocacao = dataDeLocacao;
    }

    public LocalDate getDataDeRetorno() {
        return dataDeRetorno;
    }

    public void setDataDeRetorno(LocalDate dataDeRetorno) {
        this.dataDeRetorno = dataDeRetorno;
    }

    public BigDecimal getKmRodado() {
        return kmRodado;
    }

    public void setKmRodado(BigDecimal kmRodado) {
        this.kmRodado = kmRodado;
    }

    public BigDecimal getMulta() {
        return multa;
    }

    public void setMulta(BigDecimal multa) {
        this.multa = multa;
    }

    public SituacaoReserva getSituacaoReserva() {
        return situacaoReserva;
    }

    public void setSituacaoReserva(SituacaoReserva situacaoReserva) {
        this.situacaoReserva = situacaoReserva;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reserva reserva = (Reserva) o;
        return id.equals(reserva.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

