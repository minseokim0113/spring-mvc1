package hello.itemmall.domain.Item;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Repository;

@Repository
public class ItemRepository {

	private static final ConcurrentMap<Long, Item> store = new ConcurrentHashMap<>();
	private static final AtomicLong sequence = new AtomicLong(0L);
	
	public Item save(Item item) {
		
		item.setId(sequence.incrementAndGet());
		store.put(item.getId(), item);
		
		return item;
	}
	
	public List<Item> findAll() {
		return new ArrayList<>(store.values());
	}
	
	public Item findById(long itemId) {
		
		return store.get(itemId);
	}
	
	public boolean update(long itemId, Item updateItem) {

		return store.replace(itemId, new Item(itemId, updateItem.getItemName(), updateItem.getPrice(), updateItem.getQuantity())) != null;
	}
	
	public boolean delete(long itemId) {
		return store.remove(itemId) != null;
	}
	
	public void clearStore() {
		store.clear();
	}
}
