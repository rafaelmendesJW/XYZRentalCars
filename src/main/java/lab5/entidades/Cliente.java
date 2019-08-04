package lab5.entidades;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
public class Cliente implements EntidadeBase {
    @Id @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nome;
    private String cpf;
    private String cnh;
    @Column (name = "validade_cnh")
    private LocalDate validadeCnh;
    @Column (name = "categoria_cnh")
    private String categoriaCnh;
    @OneToMany (mappedBy = "cliente")
    Set<Reserva> historicoReservas = new HashSet<>();


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getCnh() {
        return cnh;
    }

    public void setCnh(String cnh) {
        this.cnh = cnh;
    }

    public LocalDate getValidadeCnh() {
        return validadeCnh;
    }

    public void setValidadeCnh(LocalDate validadeCnh) {
        this.validadeCnh = validadeCnh;
    }

    public String getCategoriaCnh() {
        return categoriaCnh;
    }

    public void setCategoriaCnh(String categoriaCnh) {
        this.categoriaCnh = categoriaCnh;
    }

    public boolean verifSeClienteTemReserva (){
        for (Reserva reserva : historicoReservas) {
            if(reserva.getSituacaoReserva() != SituacaoReserva.finalizada){
                return true;
            }
        }
        return false;
    }
    public boolean verifValidadeCnh (){
        if(LocalDate.now().isAfter(validadeCnh)){
            return true;
        }
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cliente cliente = (Cliente) o;
        return id.equals(cliente.id);
    }

    public boolean addHistReserva(Reserva reserva) {
        return historicoReservas.add(reserva);
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
