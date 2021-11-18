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
        ShoppingCart create(@PathVariable Long idClient) {
                return service.create(idClient);
        }

        @GetMapping("/client/{idClient}")
        List<ShoppingCart> allByIdClient(@PathVariable Long idClient) {
                return service.findAll(idClient);
        }

        @GetMapping("/client/{idClient}/status/{status}")
        List<ShoppingCart> allByIdClientAndStatus(@PathVariable Long idClient, @PathVariable int status) {
                return service.findAllByIdClientAndStatus(idClient, status);
        }

        @GetMapping("/client/{idClient}/amount")
        String allAmountByClient(@PathVariable Long idClient) {
                BigDecimal amount = service.getAverageTicketAmount(idClient);
                return amount.toString();
        }

        @PutMapping("/client/{idClient}/invalidate")
        boolean invalidate(@PathVariable Long idClient) {
               return service.invalidate(idClient);
        }

        @PostMapping("/client/{idClient}/items")
        ShoppingCart addItem(@PathVariable Long idClient, @RequestBody Item item ) {
                return service.addItem(idClient, item);
        }

        @DeleteMapping("/client/{idClient}/items/{code}")
        ShoppingCart removeItem(@PathVariable Long idClient, @PathVariable Long code ) {
                return service.removeItem(idClient, code);
        }

        @GetMapping("/amount")
        String allAmount() {
                BigDecimal amount = service.getAverageTicketAmount();
                return amount.toString();
        }
}