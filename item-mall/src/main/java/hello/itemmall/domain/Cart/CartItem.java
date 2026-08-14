package hello.itemmall.domain.Cart;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartItem {
    private Long id;
    private Long cartId;
    private Long itemId;
    private Integer quantity;

    public CartItem() {

    }

    public CartItem(Long cartId, Long itemId, Integer quantity) {

        this.cartId = cartId;
        this.itemId = itemId;
        this.quantity = quantity;
    }
}
