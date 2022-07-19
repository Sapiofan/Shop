package com.example.shopjava.repos;

import com.example.shopjava.entities.user.cart.CartProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface CartProductRepo extends JpaRepository<CartProduct, Long> {

    @Query("select cp from CartProduct cp where cp.product.id=:productId and cp.cart.id=:cartId")
    CartProduct findCartProduct(Long productId, Long cartId);

    @Query("delete from CartProduct cp where cp.product.id=:productId and cp.cart.id=:cartId")
    @Modifying
    void deleteCartProduct(Long productId, Long cartId);
}
