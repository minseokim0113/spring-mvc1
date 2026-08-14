package hello.itemmall.web.shop;

import hello.itemmall.domain.Cart.CartItem;
import hello.itemmall.domain.Item.Item;
import hello.itemmall.service.cart.dto.command.CartSaveCommand;
import hello.itemmall.service.cart.CartService;
import hello.itemmall.service.cart.dto.info.CartShowInfo;
import hello.itemmall.service.cart.dto.info.ItemStatus;
import hello.itemmall.service.item.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 지금 HTML이 기대하는 백엔드 데이터만 맞춰주면 됩니다.
 * items.html: items
 * item.html: item
 * cart.html: cartItems, totalQuantity, totalPrice
 * checkout.html: orderItems, totalQuantity, totalPrice
 * cartItems / orderItems 각 원소는 최소한 itemId, itemName, price, quantity, totalPrice 프로퍼티가 있으면 됩니다.
 */

@Controller
@RequestMapping("/shop/items")
@RequiredArgsConstructor
public class ShopController {

    private final ItemService itemService;
    private final CartService cartService;

    @GetMapping
    public String items(Model model) {

        List<Item> items = itemService.findItems();
        model.addAttribute("items", items);

        return "shop/items";
    }

    @GetMapping("/{itemId}")
    public String item(@PathVariable long itemId, Model model) {

        Item item = itemService.findItem(itemId);
        model.addAttribute("item", item);

        return "shop/item";
    }

    @PostMapping("/{itemId}/cart")
    public String saveCart(@PathVariable Long itemId, @RequestParam Integer quantity, Model model) {
        try {
            cartService.saveCart(new CartSaveCommand(itemId, quantity), 1L);

            return "redirect:/shop/items/cart";

        } catch (IllegalArgumentException e) {
            Item item = itemService.findItem(itemId);

            model.addAttribute("item", item);
            model.addAttribute("errorMessage", e.getMessage());

            return "shop/item";
        }
    }

    @GetMapping("/cart")
    public String cart(Model model) {

        List<CartShowInfo> cartItems = cartService.findCartItems(1L);
        Integer totalPrice = calculateTotalPrice(cartItems);

        model.addAttribute("cartItems", cartItems);
        model.addAttribute("totalQuantity", cartItems.size());
        model.addAttribute("totalPrice", totalPrice);

        return "shop/cart";
    }

    @PostMapping("/cart/{cartItemId}/remove")
    public String removeCartItem(@PathVariable Long cartItemId) {

        cartService.removeCartItem(cartItemId);

        return "redirect:/shop/items/cart";
    }

    @GetMapping("/checkout")
    public String checkout() {
        return "shop/checkout";
    }

    private Integer calculateTotalPrice(List<CartShowInfo> cartItems) {
        Integer totalPrice = 0;

        for(CartShowInfo item : cartItems) {
            if (item.getStatus() == ItemStatus.AVAILABLE) {
                    totalPrice += item.getTotalPrice();
            }
        }

        return totalPrice;
    }
}
