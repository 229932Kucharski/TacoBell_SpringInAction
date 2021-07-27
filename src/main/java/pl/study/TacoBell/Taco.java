package pl.study.TacoBell;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@Data
public class Taco {

    private Long id;
    private Date createdAt;

    @NotNull
    @Size(min=5, message="Nazwa musi składać się z przynajmniej pięcu znaków.")
    private String name;
    @NotNull
    @Size(min=1, message="Musisz wybrac przynajmniej jeden składnik.")
    private List<String> ingredients;

}
