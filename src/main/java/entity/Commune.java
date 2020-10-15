package entity;

import javax.persistence.*;
import java.util.List;

@Entity
public class Commune {
    Long code;
    String name;

    public Commune(Long code, String name) {
        this.code = code;
        this.name = name;
    }

    public Commune(){}
    public Commune(Long code){
        this.code = code;
    }

    @OneToMany(mappedBy = "commune")
    public List<CommunePart> getCommuneParts() {
        return communeParts;
    }

    public void setCommuneParts(List<CommunePart> communeParts) {
        this.communeParts = communeParts;
    }

    List<CommunePart> communeParts;
    @Id
    public Long getCode() {
        return code;
    }

    public void setCode(Long code) {
        this.code = code;
    }

    @Column
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Commune{" +
                "code=" + code +
                ", name='" + name + '\'' +
                '}';
    }
}
