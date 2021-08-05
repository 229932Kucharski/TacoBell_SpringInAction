package pl.study.TacoBell.data;

import org.springframework.data.repository.CrudRepository;
import pl.study.TacoBell.User;

public interface UserRepository extends CrudRepository<User, Long> {
    User findByUsername(String username);
}
