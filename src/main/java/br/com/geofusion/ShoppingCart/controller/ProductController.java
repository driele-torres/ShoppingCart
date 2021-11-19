package br.com.geofusion.ShoppingCart.controller;

import java.util.List;

import br.com.geofusion.ShoppingCart.repository.ProductRepository;
import br.com.geofusion.ShoppingCart.exception.ProductNotFoundException;
import br.com.geofusion.ShoppingCart.model.Product;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value="/products")
class ProductController {
        private final ProductRepository repository;

        ProductController(ProductRepository repository) {
                this.repository = repository;
        }

        @GetMapping()
        List<Product> all() {
                return repository.findAll();
        }

        @PostMapping()
        Product getNewProduct(@RequestBody Product newProduct) {
                return repository.save(newProduct);
        }

        @GetMapping("/{productId}")
        Product one(@PathVariable String productId) {
                return repository.findById(productId)
                        .orElseThrow(() -> new ProductNotFoundException(productId));
        }

        @PutMapping("/{productId}")
        Product replaceProduct(@RequestBody Product newProduct, @PathVariable String productId) {
                return repository.findById(productId)
                        .map(product -> {
                                product.setDescription(newProduct.getDescription());
                                product.setAmountAvailable(newProduct.getAmountAvailable());
                                product.setAmountPending(newProduct.getAmountPending());
                                product.setPriceCurrent(newProduct.getPriceCurrent());
                                return repository.save(product);
                        })
                        .orElseGet(() -> {
                                newProduct.setId(productId);
                                return repository.save(newProduct);
                        });
        }

        @DeleteMapping("/{productId}")
        void deleteProduct(@PathVariable String productId) {
                repository.deleteById(productId);
        }
}