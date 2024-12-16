package com.example.shopapp.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;
import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetailDTO {
    @JsonProperty("order_id")
    @Min(value = 1, message = "Order's ID must be > 0")
    private Long orderId;

    @JsonProperty("product_id")
    @Min(value = 1, message = "Product's ID must be > 0")
    private Long productId;

    @Min(value = 0, message = "Price must be > 0")
    private Long price;

    @JsonProperty("number_of_product")
    @Min(value = 1, message = "number_of_product must be > 0")
    private int numberOfProduct;

    @JsonProperty("total_money")
    @Min(value = 0, message = "totalMoney must be > 0")
    private Float totalMoney;

    private String color;
}
