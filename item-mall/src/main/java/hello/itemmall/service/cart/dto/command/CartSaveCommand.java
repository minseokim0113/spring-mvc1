package hello.itemmall.service.cart.dto.command;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartSaveCommand {
    private Long itemId;
    private Integer quantity;

    public CartSaveCommand(Long itemId, Integer quantity) {
        this.itemId = itemId;
        this.quantity = quantity;
    }
}
