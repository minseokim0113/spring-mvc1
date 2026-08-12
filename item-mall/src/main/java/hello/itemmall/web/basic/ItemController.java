package hello.itemmall.web.basic;

import java.util.List;

import hello.itemmall.service.item.ItemService;
import hello.itemmall.service.item.command.ItemSaveCommand;
import hello.itemmall.service.item.command.ItemUpdateCommand;
import hello.itemmall.web.basic.dto.request.ItemSaveRequest;
import hello.itemmall.web.basic.dto.request.ItemUpdateRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import hello.itemmall.domain.Item.Item;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/basic/items")
@RequiredArgsConstructor
public class ItemController {

	private final ItemService itemService;
	
	@GetMapping
	public String items(Model model) {
		
		List<Item> items = itemService.findItems();
		model.addAttribute("items", items);
		
		return "basic/items";
	}
	
	@GetMapping("/{itemId}")
	public String item(@PathVariable("itemId") Long itemId, Model model) {
		
		Item item = itemService.findItem(itemId);
		model.addAttribute("item", item);
		
		return "basic/item";
	}
	
	@GetMapping("/{itemId}/edit")
	public String editForm(@PathVariable("itemId") long itemId, Model model) {
		Item item = itemService.findItem(itemId);
		model.addAttribute("item", item);
		
		return "basic/editForm";
	}
	
	@PostMapping("/{itemId}/edit")
	public String editItem(@Validated @ModelAttribute ItemUpdateRequest item, @PathVariable("itemId") long itemId) {

		itemService.updateItem(itemId, toUpdateCommand(item));
		
		return "redirect:/basic/items/{itemId}";
	}
	
	@GetMapping("/add")
	public String addForm() {
		return "basic/addForm";
	}
	
	@PostMapping("/add")
	public String addItem(@Validated ItemSaveRequest item, RedirectAttributes redirectAttributes) {
		Item savedItem = itemService.saveItem(toSaveCommand(item));
		
		redirectAttributes.addAttribute("itemId", savedItem.getId());
		redirectAttributes.addAttribute("status", true);
		
		return "redirect:/basic/items/{itemId}";
	}
	
	@PostMapping("/{itemId}/delete")
	public String deleteItem(@PathVariable("itemId") long itemId) {
		itemService.deleteItem(itemId);
		
		return "redirect:/basic/items";
	}

	private ItemUpdateCommand toUpdateCommand(ItemUpdateRequest request) {
		ItemUpdateCommand command = new ItemUpdateCommand();

		command.setItemName(request.getItemName());
		command.setPrice(request.getPrice());
		command.setQuantity(request.getQuantity());

		return command;
	}

	private ItemSaveCommand toSaveCommand(ItemSaveRequest request) {
		ItemSaveCommand command = new ItemSaveCommand();

		command.setItemName(request.getItemName());
		command.setPrice(request.getPrice());
		command.setQuantity(request.getQuantity());

		return command;
	}
}
