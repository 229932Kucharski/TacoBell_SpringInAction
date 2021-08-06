package pl.study.TacoBell.data;

import org.springframework.data.repository.CrudRepository;
import pl.study.TacoBell.Order;

public interface OrderRepository extends CrudRepository<Order, Long> {
}
