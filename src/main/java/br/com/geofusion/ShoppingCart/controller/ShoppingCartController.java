package br.com.geofusion.ShoppingCart.controller;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PostMapping;
import br.com.geofusion.ShoppingCart.model.Item;
import br.com.geofusion.ShoppingCart.model.ShoppingCart;
import br.com.geofusion.ShoppingCart.service.ShoppingCartService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value="/carts")
class ShoppingCartController {
        private final ShoppingCartService service;

        ShoppingCartController(ShoppingCartService service) {
                this.service = service;
        }

        @PutMapping("/client/{clientId}/create")
        ShoppingCart create(@PathVariable String clientId) {
                return service.create(clientId);
        }

        @GetMapping("/client/{clientId}")
        List<ShoppingCart> findByIdClient(@PathVariable String clientId) {
                return service.findByIdClient(clientId);
        }

        @GetMapping("/client/{clientId}/status/{status}")
        List<ShoppingCart> findByIdClientAndStatus(@PathVariable String clientId, @PathVariable int status) {
                return service.findByIdClientAndStatus(clientId, status);
        }

        @PutMapping("/client/{clientId}/checkout")
        boolean invalidate(@PathVariable String clientId) {
               return service.invalidate(clientId);
        }

        @PostMapping("/client/{clientId}/items")
        ShoppingCart addItem(@PathVariable String clientId, @RequestBody Item item ) {
                return service.addItem(clientId, item);
        }

        @DeleteMapping("/client/{idClient}/items/{code}")
        ShoppingCart removeItem(@PathVariable String idClient, @PathVariable Long code ) {
                return service.removeItem(idClient, code);
        }

        @GetMapping("/amount")
        String allAmount() {
                BigDecimal amount = service.getAverageTicketAmount();
                return amount.toString();
        }
}