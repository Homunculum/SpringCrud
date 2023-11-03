package com.tobeto.spring.b.controller;

import com.tobeto.spring.b.module.Person;
import com.tobeto.spring.b.module.Product;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/products")
public class ProductContreller {
    List<Product> inProductList = new ArrayList<>();

    @GetMapping
    public List<Product> getInProductList() {
        return inProductList;
    }

    @GetMapping("{id}")
    public Product getId(@PathVariable int id) {
        Product product = inProductList
                .stream()
                .filter((p) -> p.getId() == id)
                .findFirst()
                .orElseThrow();
        return product;
    }

    @PostMapping
    public void setInProductList(@RequestBody Product product) {
        inProductList.add(product);
    }

    @PutMapping("{id}")
    public String updateProduct(@PathVariable int id, @RequestBody Product product) {
        for (Product products : inProductList) {
            if (products.getId() == id) {
                products.setName(product.getName());
                products.setPrice(product.getPrice());
                return "Kullanıcı güncellendi: " +
                        "ID: " + id +
                        ", Ürün: " + products.getName() +
                        ", Fiyat: " + products.getPrice();

            }
        }
        return null;
    }

    @DeleteMapping("{id}")
    public String deleteProduct(@PathVariable int id) {
        for (Product products : inProductList) {
            if (products.getId() == id) {
                inProductList.remove(products);
                return products.getName() + " Silindi";
            } else {
                return "Ürün bulunamadı";
            }
        }
        return null;
    }
}
