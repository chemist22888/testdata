package entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class CommunePart {
    private Long code;
    private String name;

    public CommunePart(Long code, String name, Commune commune) {
        this.code = code;
        this.name = name;
        this.commune = commune;
    }
    public CommunePart(){

    }

    @Id
    public Long getCode() {
        return code;
    }

    public void setCode(Long code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @ManyToOne
    @JoinColumn(name = "commune_code",referencedColumnName = "code")
    public Commune getCommune() {
        return commune;
    }

    public void setCommune(Commune commune) {
        this.commune = commune;
    }

    private Commune commune;

    @Override
    public String toString() {
        return "CommunePart{" +
                "code=" + code +
                ", name='" + name + '\'' +
                ", commune=" + commune.code +
                '}';
    }
}
