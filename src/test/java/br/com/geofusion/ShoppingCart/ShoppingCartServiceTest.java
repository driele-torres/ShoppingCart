package br.com.geofusion.ShoppingCart;

import br.com.geofusion.ShoppingCart.exception.ClientNotFoundException;
import br.com.geofusion.ShoppingCart.model.Item;
import br.com.geofusion.ShoppingCart.model.Product;
import br.com.geofusion.ShoppingCart.model.ShoppingCart;
import br.com.geofusion.ShoppingCart.model.StatusShoppingCart;
import br.com.geofusion.ShoppingCart.service.ShoppingCartService;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest(properties = "spring.profiles.active=test")
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ShoppingCartServiceTest {
    @Autowired
    private ShoppingCartService shoppingCartService;
    private final String clientId = "2338";

    @Test
    @Order(1)
    public void createShoppingCartByIdClient(){
        ShoppingCart shoppingCart = shoppingCartService.create(clientId);
        assertNotNull(shoppingCart);
        assertEquals(shoppingCart.getStatus(), StatusShoppingCart.ACTIVE);
        assertNotEquals(shoppingCart.getId(), 0);
    }

    @Test
    @Order(2)
    public void checkShoppingCartActiveByIdClient(){
        ShoppingCart shoppingCart = shoppingCartService.create(clientId);
        assertNotNull(shoppingCart);
        assertEquals(shoppingCart.getStatus(), StatusShoppingCart.ACTIVE);
        assertEquals(shoppingCart.getId(), 1 );
    }

    @Test
    @Order(3)
    void testExceptionIsThrown() {
        var clientInvalid = "";
        assertThrows(ClientNotFoundException.class, () -> shoppingCartService.create(clientInvalid));
    }

    @Test
    @Order(4)
    public void checkShoppingCartActiveWasCreated(){
        List<ShoppingCart> shoppingCarts = shoppingCartService.findByIdClientAndStatus(clientId, StatusShoppingCart.ACTIVE.ordinal());
        boolean oneItem = (shoppingCarts.size() == 1);
        assertTrue(oneItem);
        if (oneItem){
            ShoppingCart shoppingCart = shoppingCarts.get(0);
            assertNotNull(shoppingCart);
            assertEquals(shoppingCart.getStatus(), StatusShoppingCart.ACTIVE);
            assertEquals(shoppingCart.getId(), 1 );
        }
    }

    @Test
    @Order(5)
    public void invalidateShoppingCart(){
        assertTrue(shoppingCartService.invalidate(clientId));
        List<ShoppingCart> shoppingCarts = shoppingCartService.findByIdClientAndStatus(clientId, StatusShoppingCart.ACTIVE.ordinal());
        assertTrue(shoppingCarts.size() == 0);
    }

    @Test
    @Order(6)
    public void createNewShoppingCartByIdClient(){
        ShoppingCart shoppingCart = shoppingCartService.create(clientId);
        assertNotNull(shoppingCart);
        assertEquals(shoppingCart.getStatus(), StatusShoppingCart.ACTIVE);
        assertEquals(shoppingCart.getId(), 2);
    }

    @Test
    @Order(7)
    public void addItemShoppingCartByIdClient(){
        Product  product = new Product("4238");
        Item item = new Item( product , new BigDecimal("4.56"), 215 );
        assertTrue(this.shoppingCartService.addItem( clientId, item ));
    }

    @Test
    @Order(8)
    public void checkItemInShoppingCartByIdClient(){
        checkItemInShoppingCart(1);
    }

    @Test
    @Order(9)
    public void addItemShoppingCartByIdClientWithNoStock(){
        Product  product = new Product("4238");
        Item item = new Item( product , new BigDecimal("4.56"), 456 );
        assertFalse(this.shoppingCartService.addItem( clientId, item ));
    }

    @Test
    @Order(10)
    public void checkItemStillInShoppingCartByIdClient(){
        checkItemInShoppingCart(1);
    }

    @Test
    @Order(11)
    public void removeItemShoppingCartByIdClient(){
        assertTrue(this.shoppingCartService.removeItem( clientId, "4238" ));
    }

    @Test
    @Order(12)
    public void checkItemIsNotInShoppingCartByIdClient(){
        checkItemInShoppingCart(0);
    }

    private void checkItemInShoppingCart(int expected) {
        List<Item> items = this.shoppingCartService.findShoppingCartActiveByByIdClientItems(clientId);
        assertEquals(items.size(), expected);
    }
}