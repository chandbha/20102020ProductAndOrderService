package com.ibm.orderservice.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.orderservice.entity.Cart;
import com.ibm.orderservice.entity.Item;
import com.ibm.orderservice.entity.Product;
import com.ibm.orderservice.repository.CartRepository;
import com.ibm.orderservice.restclient.ProductClient;

@Service
public class CartServiceImpl implements CartService{

	 @Autowired
	    private ProductClient productClient;

	    @Autowired
	    private CartRepository cartRepository;

	    @Override
	    public void addItemToCart(String cartId, Long productId, Integer quantity) {
	        Product product = productClient.getProductById(productId).getBody();
	        Item item = new Item(quantity,product, CartServiceImpl.getSubTotalForItem(product,quantity));
	        Cart cartEntity = new Cart();
	        cartEntity.setCartId(Long.parseLong(cartId));
	        List<Item> itemlist = new ArrayList<Item>();
	        itemlist.add(item);
	        cartEntity.setItems(itemlist);
	        cartEntity.setQuantity(quantity);
	        cartRepository.save(cartEntity);
	    }

	    @Override
	    public List<Object> getCart(String cartId) {
	        return (List<Object>)cartRepository.findById(Long.parseLong(cartId)).get();
	    }

	    @Override
	    public void changeItemQuantity(String cartId, Long productId, Integer quantity) {
//	        List<Item> cart = (List)cartRedisRepository.getCart(cartId, Item.class);
//	        for(Item item : cart){
//	            if((item.getProduct().getId()).equals(productId)){
//	                cartRedisRepository.deleteItemFromCart(cartId, item);
//	                item.setQuantity(quantity);
//	                item.setSubTotal(CartUtilities.getSubTotalForItem(item.getProduct(),quantity));
//	                cartRedisRepository.addItemToCart(cartId, item);
//	            }
//	        }
	    }

	    @Override
	    public void deleteItemFromCart(String cartId, Long productId) {
//	        List<Item> cart = (List) cartRedisRepository.getCart(cartId, Item.class);
//	        for(Item item : cart){
//	            if((item.getProduct().getId()).equals(productId)){
//	                cartRedisRepository.deleteItemFromCart(cartId, item);
//	            }
//	        }
	    }

	    @Override
	    public boolean checkIfItemIsExist(String cartId, Long productId) {
//	        List<Item> cart = (List) cartRedisRepository.getCart(cartId, Item.class);
//	        for(Item item : cart){
//	            if((item.getProduct().getId()).equals(productId)){
//	                return true;
//	            }
//	        }
	        return false;
	    }

	    @Override
	    public List<Item> getAllItemsFromCart(String cartId) {
	        List<Item> items = (List)cartRepository.findAll();
	        return items;
	    }

	    @Override
	    public void deleteCart(String cartId) {
	    	cartRepository.deleteById(Long.parseLong(cartId));
	    }

	    public static BigDecimal getSubTotalForItem(Product product, int quantity){
	        return (product.getPrice()).multiply(BigDecimal.valueOf(quantity));
	     }
}
