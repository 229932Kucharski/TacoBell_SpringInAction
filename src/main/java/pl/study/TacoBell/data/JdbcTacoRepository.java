package pl.study.TacoBell.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import pl.study.TacoBell.Ingredient;
import pl.study.TacoBell.Taco;

import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.Arrays;
import java.util.Date;

@Repository
public class JdbcTacoRepository implements  TacoRepository {

    private JdbcTemplate jdbc;
    private final IngredientRepository ingredientRepo;

    @Autowired
    public JdbcTacoRepository(JdbcTemplate jdbc, IngredientRepository ingredientRepo) {
        this.jdbc = jdbc;
        this.ingredientRepo = ingredientRepo;
    }

    @Override
    public Taco save(Taco taco) {
        long tacoId = saveTacoInfo(taco);
        taco.setId(tacoId);
        for(String ingredientId : taco.getIngredients()){
            Ingredient ingredient = ingredientRepo.findById(ingredientId);
            saveIngredientToTaco(ingredient, tacoId);
        }
        return taco;
    }

    private long saveTacoInfo(Taco taco) {
        taco.setCreatedAt(new Date());
        PreparedStatementCreatorFactory pscf = new PreparedStatementCreatorFactory(
                "Insert into Taco(name, createdAt) values(?,?)", Types.VARCHAR, Types.TIMESTAMP);
        pscf.setReturnGeneratedKeys(Boolean.TRUE);
        PreparedStatementCreator psc = pscf.newPreparedStatementCreator(
                Arrays.asList(taco.getName(), new Timestamp(taco.getCreatedAt().getTime())));
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbc.update(psc, keyHolder);
        return keyHolder.getKey().longValue();
    }

    private void saveIngredientToTaco(Ingredient ingredient, long tacoId) {
        jdbc.update("insert into Taco_Ingredients (taco, ingredient) values (?, ?)",
                tacoId, ingredient.getId());
    }
}
