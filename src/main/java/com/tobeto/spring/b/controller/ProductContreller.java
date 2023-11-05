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
    public String setInProductList(@RequestBody Product product) {
        inProductList.add(product);
        return "Ürün eklendi: " +
                "ID: " + product.getId() +
                ", Ürün: " + product.getName() +
                ", Fiyat: " + product.getPrice();
    }

    @PutMapping("{id}")
    public String updateProduct(@PathVariable int id, @RequestBody Product product) {
        Product products = inProductList.stream()  //for döngüsü yerine burası
                .filter((p) -> p.getId() == id).findFirst()  //if döngüsü için burası
                .orElseThrow(); //ürün bulunamadığında null döndür
        products.setName(product.getName());
        products.setPrice(product.getPrice());

        return "Ürün güncellendi: " +
                "ID: " + id +
                ", Ürün: " + products.getName() +
                ", Fiyat: " + products.getPrice();
    }

        /*for (Product products : inProductList) {
            if (products.getId() == id) {
                products.setName(product.getName());
                products.setPrice(product.getPrice());
                return "Ürün güncellendi: " +
                        "ID: " + id +
                        ", Ürün: " + products.getName() +
                        ", Fiyat: " + products.getPrice();

            }
        }
        return null;
}*/

    @DeleteMapping("{id}")
    public String deleteProduct(@PathVariable int id) {
        Product products = inProductList.stream()  //for döngüsü yerine burası
                .filter((p) -> p.getId() == id).findFirst()  //if döngüsü için burası
                .orElseThrow(); //ürün bulunamadığında null döndür
        inProductList.remove(products);
        return products.getName() + " Silindi";
        /*for (Product products : inProductList) {
            if (products.getId() == id) {
                inProductList.remove(products);
                return products.getName() + " Silindi";
            } else {
                return "Ürün bulunamadı";
            }
        }
        return null;*/
    }
}
