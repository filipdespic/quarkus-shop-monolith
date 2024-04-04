package controller;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import service.OrderItemService;
import service.dtos.OrderItemDto;

import java.util.List;

@Path("/order-items")
public class OrderItemResource {
    @Inject
    OrderItemService itemService;
    @GET
    @Path("/order/{id}")
    public List<OrderItemDto> findByOrderId(@PathParam("id") Long id) {
        return this.itemService.findByOrderId(id);
    }
    @GET
    @Path("/{id}")
    public OrderItemDto findById(@PathParam("id") Long id) {
        return this.itemService.findById(id);
    }
    @POST
    public OrderItemDto create(OrderItemDto orderItemDto) {
        return this.itemService.create(orderItemDto);
    }
    @DELETE
    @Path("/{id}")
    public void delete(@PathParam("id") Long id) {
        this.itemService.delete(id);
    }
}
