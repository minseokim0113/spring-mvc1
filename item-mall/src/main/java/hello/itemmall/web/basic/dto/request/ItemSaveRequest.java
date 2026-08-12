package hello.itemmall.web.basic.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
public class ItemSaveRequest {
    @NotBlank
    private String itemName;

    @NotNull
    @Positive
    private Integer price;

    @NotNull
    @PositiveOrZero
    private Integer quantity;
}
