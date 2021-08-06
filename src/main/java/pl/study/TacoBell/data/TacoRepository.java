package pl.study.TacoBell.data;

import org.springframework.data.repository.CrudRepository;
import pl.study.TacoBell.Taco;

public interface TacoRepository extends CrudRepository<Taco, Long> {
}
