package hello.itemmall.service.cart;

import hello.itemmall.domain.Cart.CartItem;
import hello.itemmall.domain.Cart.CartItemRepository;
import hello.itemmall.domain.Cart.CartRepository;
import hello.itemmall.domain.Item.Item;
import hello.itemmall.domain.Item.ItemRepository;
import hello.itemmall.service.cart.dto.command.CartSaveCommand;
import hello.itemmall.service.cart.dto.info.CartShowInfo;
import hello.itemmall.service.cart.dto.info.ItemStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CartService {

    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final ItemRepository itemRepository;

    public CartItem saveCart(CartSaveCommand command, Long customerId) {

        Long cartId = cartRepository.findByCustomerId(customerId);
        Item item = itemRepository.findById(command.getItemId());
        CartItem existing = cartItemRepository.findByCartIdAndItemId(cartId, command.getItemId());

        if (existing != null) {
            int quantity = existing.getQuantity() + command.getQuantity();

            if (quantity <= item.getQuantity() && quantity > 0) {
                existing.setQuantity(quantity);
                return existing;

            } else if (quantity > 0) {
                throw new IllegalArgumentException("재고보다 많이 담을 수 없습니다.");

            } else {
                throw new IllegalArgumentException("수량이 음수이면 안됩니다.");
            }
        }

        int quantity = command.getQuantity();

        if (quantity <= item.getQuantity() && quantity > 0) {
            return cartItemRepository.save(new CartItem(cartId, command.getItemId(), command.getQuantity()));
        } else if (quantity > 0){
            throw new IllegalArgumentException("재고보다 많이 담을 수 없습니다.");
        } else {
            throw new IllegalArgumentException("수량이 음수이면 안됩니다.");
        }
    }

    public List<CartShowInfo> findCartItems(Long customerId) {

        Long cartId = cartRepository.findByCustomerId(customerId);

         List<CartItem> cartItems = cartItemRepository.findAllByCartId(cartId);

         List<CartShowInfo> infos = new ArrayList<>();

         for(CartItem cartItem : cartItems) {
             CartShowInfo info = new CartShowInfo();
             Item item = itemRepository.findById(cartItem.getItemId());

             if(item == null) {
                 info.setId(cartItem.getId());
                 info.setItemId(cartItem.getItemId());
                 info.setStatus(ItemStatus.DELETED);
                 info.setQuantity(cartItem.getQuantity());
             } else if (item.getQuantity() == 0) {
                 info.setId(cartItem.getId());
                 info.setItemId(cartItem.getItemId());
                 info.setItemName(item.getItemName());
                 info.setPrice(item.getPrice());
                 info.setStatus(ItemStatus.SOLD_OUT);
                 info.setQuantity(cartItem.getQuantity());
                 info.setTotalPrice(item.getPrice() * cartItem.getQuantity());
             } else {
                 info.setId(cartItem.getId());
                 info.setItemId(cartItem.getItemId());
                 info.setItemName(item.getItemName());
                 info.setPrice(item.getPrice());
                 info.setStatus(ItemStatus.AVAILABLE);
                 info.setQuantity(cartItem.getQuantity());
                 info.setTotalPrice(item.getPrice() * cartItem.getQuantity());
             }

             infos.add(info);
         }

         return infos;

    }

    public void removeCartItem(Long cartItemId) {
        cartItemRepository.removeCartItem(cartItemId);
    }
}
