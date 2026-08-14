package hello.itemmall.domain.Cart;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@Repository
public class CartRepository {

    private static final ConcurrentMap<Long, Cart> store = new ConcurrentHashMap<>();

    @PostConstruct
    public void init() {
        store.put(1L, new Cart(1L, 1L));
    }

    public Long findByCustomerId(Long customerId) {

        for (Cart c : store.values()) {
            if (c.getCustomerId().equals(customerId)) {
                return c.getId();
            }
        }

        return null;
    }
}
