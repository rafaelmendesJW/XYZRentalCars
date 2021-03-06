package lab5.entidades;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

@Entity
public class Reserva implements EntidadeBase {
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

    public Reserva(){
        this.setSituacaoReserva(SituacaoReserva.ativa);
    }


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

    public void ativar() {

        if (!cliente.verifSeClienteTemReserva()) {
            setSituacaoReserva(SituacaoReserva.ativa);
        }else if(!cliente.verifValidadeCnh()){
            setSituacaoReserva(SituacaoReserva.ativa);
        } else {
          throw new IllegalStateException("Já tem uma reserva ativa!");
        }
    }

    public void finalizar(Sede sede) {
        valorTotal = carro.getValorDiarias().multiply(new BigDecimal(qtdDeDiarias));
        if(!sedeDeLocacao.equals(sede)) {
            valorTotal = valorTotal .add(multa);
        }
        setSituacaoReserva(situacaoReserva.finalizar());
    }

    public void notificarAtraso() {
        setSituacaoReserva(situacaoReserva.notificarAtraso());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reserva reserva = (Reserva) o;
        return Objects.equals(id, reserva.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Sede getSedeDeLocacao() {
        return sedeDeLocacao;
    }

    public void setSedeDeLocacao(Sede sedeDeLocacao) {
        this.sedeDeLocacao = sedeDeLocacao;
    }

    public Sede getSedeDeDevolucao() {
        return sedeDeDevolucao;
    }

    public void setSedeDeDevolucao(Sede sedeDeDevolucao) {
        this.sedeDeDevolucao = sedeDeDevolucao;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        if(cliente.verifSeClienteTemReserva()) {
            throw new IllegalArgumentException();
        }else if(cliente.verifValidadeCnh()){
            throw new IllegalArgumentException();
        }
        else {
            this.cliente = cliente;
            cliente.addHistReserva(this);
        }
    }

    public Carro getCarro() {
        return carro;
    }

    public void setCarro(Carro carro) {
        if(!carro.getSituacao().equals(SituacaoCarro.alugado)){
            this.carro = carro;
        } else{
            throw new IllegalStateException();
        }

    }

    @Override
    public Integer getId() {
        return id;
    }
}

