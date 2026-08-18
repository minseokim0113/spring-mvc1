package hello.itemmall.domain.Customer;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@Repository
public class CustomerRepository {

    private static final ConcurrentMap<Long, Customer> store = new ConcurrentHashMap<>();

    @PostConstruct
    public void init() {
        store.put(1L, new Customer(1L, "Amy", 100000));
//        store.put(2L, new Customer(2L, "Tom", 100000));
    }
}
