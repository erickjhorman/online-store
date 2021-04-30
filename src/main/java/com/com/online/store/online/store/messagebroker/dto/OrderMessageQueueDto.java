package com.com.online.store.online.store.messagebroker.dto;

import com.com.online.store.online.store.dto.ProductDto;
import com.com.online.store.online.store.model.Order;
import com.com.online.store.online.store.model.Product;
import com.com.online.store.online.store.model.User;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
@ToString
@Getter
@Setter
@AllArgsConstructor
public class OrderMessageQueueDto {

    @Past(message = "Date may not in the past")
    private LocalDateTime createdAt;

    @Future(message = "{user.updated_at.past}")
    private LocalDateTime updateAt;

    @NotNull(message = "{order.productId}")
    private List<ProductDto> productId;

    @NotNull(message = "{order.userId}")
    private User userId;

    public Order toEntity() {
        return Order.builder()
                .createdAt(LocalDateTime.now())
                .updateAt(null)
                .userId(getUserId())
                .build();
    }

    public List<Product> productListToEntity(List<ProductDto> productList) {
        return productList.stream().map(p -> {
            Product product = new Product();
            product.setProductName(p.getProductName());
            product.setDescription(p.getDescription());
            product.setPrice(p.getPrice());
            product.setQuantity(p.getQuantity());
            product.setCreatedAt(LocalDateTime.now());
            product.setUpdateAt(null);
            return product;
        }).collect(Collectors.toList());
    }
}
