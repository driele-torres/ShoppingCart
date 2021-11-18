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

        @GetMapping("/{code}")
        Product one(@PathVariable Long code) {
                return repository.findById(code)
                        .orElseThrow(() -> new ProductNotFoundException(code));
        }

        @PutMapping("/{code}")
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

        @DeleteMapping("/{code}")
        void deleteProduct(@PathVariable Long code) {
                repository.deleteById(code);
        }
}