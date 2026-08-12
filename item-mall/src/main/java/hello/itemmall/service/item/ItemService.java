package hello.itemmall.service.item;

import hello.itemmall.domain.Item.Item;
import hello.itemmall.domain.Item.ItemRepository;
import hello.itemmall.service.item.command.ItemSaveCommand;
import hello.itemmall.service.item.command.ItemUpdateCommand;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    @PostConstruct
    public void init() {
        itemRepository.save(new Item("apple", 10000, 10));
        itemRepository.save(new Item("banana", 20000, 20));
    }

    public List<Item> findItems() {
        return itemRepository.findAll();
    }

    public Item findItem(Long itemId) {
        Item item = itemRepository.findById(itemId);
        if (item == null) {
            throw new IllegalArgumentException("찾으시는 id의 아이템이 존재하지 않습니다. id=" + itemId);
        }

        return item;
    }

    public Item saveItem(ItemSaveCommand item) {

        Item savedItem = itemRepository.save(toSaveItem(item));

//        if (savedItem == null) {
//            throw new IllegalArgumentException("저장이 성공적으로 이루어지지 않았습니다.");
//        }

        return savedItem;
    }

    public void updateItem(Long itemId, ItemUpdateCommand updateCommand) {

        boolean isUpdated = itemRepository.update(itemId, toUpdateItem(updateCommand));

        if (!isUpdated) {
            throw new IllegalArgumentException("업데이트가 성공적으로 이루어지지 않았습니다. id=" + itemId);
        }
    }
    public void deleteItem(Long itemId) {

        boolean isDeleted = itemRepository.delete(itemId);

        if(!isDeleted) {
            throw new IllegalArgumentException("삭제가 성공적으로 이루어지지 않았습니다. id=" + itemId);
        }
    }

    private Item toUpdateItem(ItemUpdateCommand command) {
        Item item = new Item();

        item.setItemName(command.getItemName());
        item.setPrice(command.getPrice());
        item.setQuantity(command.getQuantity());

        return item;
    }

    private Item toSaveItem(ItemSaveCommand command) {
        Item item = new Item();

        item.setItemName(command.getItemName());
        item.setPrice(command.getPrice());
        item.setQuantity(command.getQuantity());

        return item;
    }
}
