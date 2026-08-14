package hello.itemmall.domain.Cart;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
@RequiredArgsConstructor
public class CartItemRepository {

    private static final ConcurrentMap<Long, CartItem> store = new ConcurrentHashMap<>();
    private static final AtomicLong sequence = new AtomicLong(0L);

    public CartItem save(CartItem cartItem) {

        cartItem.setId(sequence.incrementAndGet());
        store.put(cartItem.getId(), cartItem);

        return cartItem;
    }

    public CartItem findByCartIdAndItemId(Long cartId, Long itemId) {
        for(CartItem c : new ArrayList<>(store.values())) {
            if(c.getCartId().equals(cartId) && c.getItemId().equals(itemId)) {
                return c;
            }
        }

        return null;
    }

    public List<CartItem> findAllByCartId(Long cartId) {

        List<CartItem> items = new ArrayList<>();

        for(CartItem c : new ArrayList<>(store.values())) {
            if(c.getCartId().equals(cartId)) {
                items.add(c);
            }
        }

        return items;
    }

    public void removeCartItem(Long cartItemId) {

        store.remove(cartItemId);
    }
}
