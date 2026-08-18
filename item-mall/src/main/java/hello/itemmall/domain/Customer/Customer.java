package hello.itemmall.domain.Customer;

import jakarta.annotation.PostConstruct;
import lombok.Getter;

@Getter
public class Customer {

    private Long id;
    private String customerName;
    private Integer balance;

    public Customer() {

    }

    public Customer(Long id, String customerName, Integer balance) {
        this.id = id;
        this.customerName = customerName;
        this.balance = balance;
    }
}
