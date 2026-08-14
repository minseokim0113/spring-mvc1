package hello.itemmall.domain.Cart;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Cart {
    private Long id;
    private Long customerId;

    public Cart() {

    }

    public Cart(Long id, Long customerId) {
        this.id = id;
        this.customerId = customerId;
    }
}
