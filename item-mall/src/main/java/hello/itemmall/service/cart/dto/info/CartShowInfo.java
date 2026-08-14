package hello.itemmall.service.cart.dto.info;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartShowInfo {
    private Long id;
    private Long itemId;
    private String itemName;
    private Integer price;
    private ItemStatus status;
    private Integer quantity;
    private Integer totalPrice;
}
