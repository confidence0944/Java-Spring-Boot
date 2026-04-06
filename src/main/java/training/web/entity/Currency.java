package training.web.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Column;

@Entity
@Table(name = "TB_Currency", schema = "dbo")
public class Currency {

    @Id
    @Column(name = "Currency")
    private String code;

    @Column(name = "CurrencyName")
    private String name;

    // Getter & Setter
    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}