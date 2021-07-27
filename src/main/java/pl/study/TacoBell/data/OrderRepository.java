package pl.study.TacoBell.data;

import pl.study.TacoBell.Order;

public interface OrderRepository {
    Order save(Order order);
}
