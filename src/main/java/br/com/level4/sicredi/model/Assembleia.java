package br.com.level4.sicredi.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
public class Assembleia {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_pauta", referencedColumnName = "id")
    private Pauta pauta;

    @OneToMany
    private Set<Voto> votos = new HashSet<Voto>();;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Pauta getPauta() {
        return pauta;
    }

    public void setPauta(Pauta pauta) {
        this.pauta = pauta;
    }

    public Set<Voto> getVotos() {
        return votos;
    }

    public void setVotos(Set<Voto> votos) {
        this.votos = votos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Assembleia that = (Assembleia) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(pauta, that.pauta) &&
                Objects.equals(votos, that.votos);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, pauta, votos);
    }

    @Override
    public String toString() {
        return "Assembleia{" +
                "id=" + id +
                ", pauta=" + pauta +
                ", votos=" + votos +
                '}';
    }
}
