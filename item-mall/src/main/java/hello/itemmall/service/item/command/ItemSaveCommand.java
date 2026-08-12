package hello.itemmall.service.item.command;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemSaveCommand {
    private String itemName;
    private Integer price;
    private Integer quantity;
}
