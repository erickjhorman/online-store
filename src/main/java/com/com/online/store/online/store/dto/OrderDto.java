package com.com.online.store.online.store.dto;

import com.com.online.store.online.store.model.Order;
import com.com.online.store.online.store.model.Product;
import com.com.online.store.online.store.model.SubProduct;
import com.com.online.store.online.store.model.User;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Range;

import javax.persistence.Column;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Positive;
import java.time.LocalDateTime;
import java.util.Currency;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@ToString
public class OrderDto {

    @Past(message = "Date may not in the past")
    private LocalDateTime createdAt;

    @Future(message = "{user.updated_at.past}")
    private LocalDateTime updateAt;

    @NotNull(message = "{order.productId}")
    private List<ProductDto> productId;

    @NotNull(message = "{payment.notnull}")
    private PaymentDto payment;

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
