package pl.study.TacoBell;

import lombok.Data;
import org.hibernate.validator.constraints.CreditCardNumber;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name="Taco_Order")
public class Order implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    private Date placedAt;

    @ManyToMany(targetEntity=Taco.class)
    private List<Taco> tacos = new ArrayList<>();

    @ManyToOne
    private User user;

    @NotBlank(message = "Podanie imienia i nazwiska jest obowiązkowe")
    private String name;
    @NotBlank(message = "Podanie ulicy jest obowiązkowe")
    private String street;
    @NotBlank(message = "Podanie miejscowosci jest obowiązkowe")
    private String city;
    @NotBlank(message = "Podanie wojewodztwa jest obowiazkowe")
    private String state;
    @NotBlank(message = "Podanie kodu pocztowego jest obowiazkowe")
    private String zip;
    @CreditCardNumber(message = "To nie jest prawidłowy numer karty kredytowej")
    private String ccNumber;
    @Pattern(regexp="^([0-1][0-9])([/])([1-9][0-9])$",
            message = "Wartosc musi byc w formacie MM/RR")
    private String ccExpiration;
    @Digits(integer=3, fraction = 0, message = "Nieprawidłowy kod CVV")
    private String ccCVV;

    public void addDesign(Taco design) {
        this.tacos.add(design);
    }

    @PrePersist
    void placedAt() {
        this.placedAt = new Date();
    }

}
