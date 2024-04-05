import io.quarkus.test.InjectMock;
import io.quarkus.test.Mock;
import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.ws.rs.core.Response;
import model.Cart;
import model.Customer;
import model.status.CartStatus;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import service.CartService;
import service.dtos.CartDto;
import service.dtos.CustomerDto;
import utils.TestContainerResource;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;
import static org.jboss.resteasy.reactive.RestResponse.Status.OK;

@QuarkusTest
@QuarkusTestResource(TestContainerResource.class)
public class CartResourceTest {

    @InjectMock
    CartService cartService;

    @Test
    void testFindAllCarts() {
        CartDto cart = new CartDto();
        cart.setStatus("NEW");
        List<CartDto> carts = List.of(cart);
        Mockito.when(cartService.findAll()).thenReturn((List<CartDto>) carts);

        when().get("/carts")
            .then()
            .statusCode(OK.getStatusCode())
            .body("size()", greaterThan(0));
    }

    @Test
    void testFindAllActiveCarts() {
        CartDto cart1 = new CartDto();
        cart1.setStatus("NEW");
        CartDto cart2 = new CartDto();
        cart2.setStatus("CANCELED");
        List<CartDto> carts = List.of(cart1, cart2);
        Mockito.when(cartService.findAllActiveCarts()).thenReturn(List.of(cart1));

        when().get("/carts/active")
                .then()
                .statusCode(OK.getStatusCode())
                .body("size()", equalTo(1))
                .body("[0].status", equalTo("NEW"));
    }

}
