
package br.com.geofusion.ShoppingCart.controller;

import java.util.List;

//import br.com.geofusion.ShoppingCart.exception.ShoppingCartNotFoundException;
//import br.com.geofusion.ShoppingCart.model.Item;
import br.com.geofusion.ShoppingCart.model.ShoppingCart;
import br.com.geofusion.ShoppingCart.repository.ShoppingCartRepository;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
class ShoppingCartController {
        private final ShoppingCartRepository repository;

        ShoppingCartController(ShoppingCartRepository repository) {
                this.repository = repository;
        }

        // Aggregate root
        // tag::get-aggregate-root[]
        @GetMapping("/client/{idCliente}/shoppingcart")
        List<ShoppingCart> all(@PathVariable Long idCliente) {
                return repository.findAll();
        }
        // end::get-aggregate-root[]
//
//        @PostMapping("/shoppingcart/{idCliente}")
//        ShoppingCart newShoppingCart(@PathVariable Long idCliente) {
////                repository.save(newShoppingCart)
//                return new ShoppingCart();
//        }
//
//        @PostMapping("/shoppingcart/items")
//        ShoppingCart addItem(@RequestBody Long idCliente) {
////                repository.save(newShoppingCart)
//                return new ShoppingCart();
//        }
//
//        @PutMapping("/shoppingcart/items")
//        ShoppingCart replaceEmployee(@RequestBody ShoppingCart newShoppingCart, @PathVariable Long id) {
//                return repository.findById(id)
//                        .map(employee -> {
//                                employee.setName(newShoppingCart.getName());
//                                employee.setRole(newShoppingCart.getRole());
//                                return repository.save(employee);
//                        })
//                        .orElseGet(() -> {
//                                newEmployee.setId(id);
//                                return repository.save(newShoppingCart);
//                        });
//        }
//
////        @PathVariable Long idCliente
//        // Single item
//
//        @GetMapping("/shoppingcart/items")
//        ShoppingCart one(@PathVariable Long id) {
//                return repository.findById(id)
//                        .orElseThrow(() -> new ShoppingCartNotFoundException(id));
//        }
//
//
//
//        @DeleteMapping("/shoppingcart/{code}")
//        void deleteEmployee(@PathVariable Long id) {
//                repository.deleteById(id);
//        }
}