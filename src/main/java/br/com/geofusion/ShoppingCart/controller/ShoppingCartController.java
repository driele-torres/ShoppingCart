package br.com.geofusion.ShoppingCart.controller;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.geofusion.ShoppingCart.model.Item;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
        ResponseEntity<ShoppingCart> create(@PathVariable String clientId) {
                ShoppingCart shoppingCart = service.create(clientId);
                return ResponseEntity.status(HttpStatus.OK).body(shoppingCart);
        }

        @GetMapping("/client/{clientId}")
        ResponseEntity<List<ShoppingCart>> findByIdClient(@PathVariable String clientId) {
                List<ShoppingCart> shoppingCarts = service.findByIdClient(clientId);
                return ResponseEntity.status(HttpStatus.OK).body(shoppingCarts);
        }

        @GetMapping("/client/{clientId}/status/{status}")
        ResponseEntity<List<ShoppingCart>> findByIdClientAndStatus(@PathVariable String clientId, @PathVariable int status) {
                List<ShoppingCart> shoppingCarts = service.findByIdClientAndStatus(clientId, status);
                return ResponseEntity.status(HttpStatus.OK).body(shoppingCarts);
        }

        @PutMapping("/client/{clientId}/checkout")
        ResponseEntity<Object> invalidate(@PathVariable String clientId) {
                if (service.invalidate(clientId))
                        return ResponseEntity.status(HttpStatus.OK).build();
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        @PostMapping("/client/{clientId}/items")
        ResponseEntity<Object> addItem(@PathVariable String clientId, @RequestBody Item item ) {
                if (service.addItem(clientId, item))
                        return ResponseEntity.status(HttpStatus.OK).build();
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        @DeleteMapping("/client/{clientId}/items/{productId}")
        ResponseEntity<Object> removeItem(@PathVariable String clientId, @PathVariable String productId ) {
                if (service.removeItem(clientId, productId))
                        return ResponseEntity.status(HttpStatus.OK).build();
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        @GetMapping("/amount")
        ResponseEntity<Object> allAmount() {
                BigDecimal amount = service.getAverageTicketAmount();
                Map<String, Object> map = new HashMap<>();
                map.put("amount", amount.toString());
                return ResponseEntity.status(HttpStatus.OK).body(map);
        }
}