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

        @PutMapping("/client/{idClient}/create")
        ShoppingCart create(@PathVariable String idClient) {
                return service.create(idClient);
        }

        @GetMapping("/client/{idClient}")
        List<ShoppingCart> allByIdClient(@PathVariable String idClient) {
                return service.findAll(idClient);
        }

        @GetMapping("/client/{idClient}/status/{status}")
        List<ShoppingCart> allByIdClientAndStatus(@PathVariable String idClient, @PathVariable int status) {
                return service.findAllByIdClientAndStatus(idClient, status);
        }

        @GetMapping("/client/{idClient}/amount")
        String allAmountByClient(@PathVariable String idClient) {
                BigDecimal amount = service.getAverageTicketAmount(idClient);
                return amount.toString();
        }

        @PutMapping("/client/{idClient}/checkout")
        boolean invalidate(@PathVariable String idClient) {
               return service.invalidate(idClient);
        }

        @PostMapping("/client/{idClient}/items")
        ShoppingCart addItem(@PathVariable String idClient, @RequestBody Item item ) {
                return service.addItem(idClient, item);
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