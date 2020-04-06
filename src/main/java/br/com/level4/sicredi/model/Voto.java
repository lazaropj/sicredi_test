package br.com.level4.sicredi.model;

import javax.persistence.*;
import java.util.Objects;


@Entity
public class Voto {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne
    private Associado associado;

    private Boolean opcao;

    public Voto() {
    }

    public Voto(Associado associado, boolean opcao) {
        this.associado = associado;
        this.opcao = opcao;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Associado getAssociado() {
        return associado;
    }

    public void setAssociado(Associado associado) {
        this.associado = associado;
    }

    public Boolean getOpcao() {
        return opcao;
    }

    public void setOpcao(Boolean opcao) {
        this.opcao = opcao;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Voto voto = (Voto) o;
        return Objects.equals(id, voto.id) &&
                Objects.equals(associado, voto.associado) &&
                Objects.equals(opcao, voto.opcao);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, associado, opcao);
    }

    @Override
    public String toString() {
        return "Voto{" +
                "id=" + id +
                ", associado=" + associado +
                ", opcao=" + opcao +
                '}';
    }
}
