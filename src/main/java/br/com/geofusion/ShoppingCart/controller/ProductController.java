package br.com.geofusion.ShoppingCart.controller;

import java.util.List;

import br.com.geofusion.ShoppingCart.repository.ProductRepository;
import br.com.geofusion.ShoppingCart.exception.ProductNotFoundException;
import br.com.geofusion.ShoppingCart.model.Product;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
class ProductController {
        private final ProductRepository repository;

        ProductController(ProductRepository repository) {
                this.repository = repository;
        }

        // Aggregate root
        // tag::get-aggregate-root[]
        @GetMapping("/products")
        List<Product> all() {
                return repository.findAll();
        }
        // end::get-aggregate-root[]

        @PostMapping("/products")
        Product getNewProduct(@RequestBody Product newProduct) {
                return repository.save(newProduct);
        }

        // Single item
        @GetMapping("/products/{code}")
        Product one(@PathVariable Long code) {
                return repository.findById(code)
                        .orElseThrow(() -> new ProductNotFoundException(code));
        }

        @PutMapping("/products/{code}")
        Product replaceProduct(@RequestBody Product newProduct, @PathVariable Long code) {
                return repository.findById(code)
                        .map(product -> {
                                product.setDescription(newProduct.getDescription());
                                product.setAmountAvailable(newProduct.getAmountAvailable());
                                product.setAmountPending(newProduct.getAmountPending());
                                product.setPriceCurrent(newProduct.getPriceCurrent());
                                return repository.save(product);
                        })
                        .orElseGet(() -> {
                                newProduct.setCode(code);
                                return repository.save(newProduct);
                        });
        }

        @DeleteMapping("/products/{code}")
        void deleteProduct(@PathVariable Long code) {
                repository.deleteById(code);
        }
}