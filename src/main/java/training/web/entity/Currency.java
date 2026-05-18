package training.web.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import jakarta.persistence.Column;

@Data
@Entity
@Table(name = "TB_Currency")
public class Currency {

    @Id
    @Column(name = "Currency")
    private String code;

    @Column(name = "CurrencyName")
    private String name;
}