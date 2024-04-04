package controller;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import service.OrderService;
import service.dtos.OrderDto;

import java.util.List;

@Path("/orders")
public class OrderResource {
    @Inject
    OrderService orderService;
    @GET
    public List<OrderDto> findAll() {
        return this.orderService.findAll();
    }
    @GET
    @Path("/customer/{id}")
    public List<OrderDto> findAllByUser(@PathParam("id") Long id) {
        return this.orderService.findAllByUser(id);
    }
    @GET
    @Path("/{id}")
    public OrderDto findById(@PathParam("id") Long id) {
        return this.orderService.findById(id);
    }
    @POST
    public OrderDto create(OrderDto orderDto) {
        return this.orderService.create(orderDto);
    }
    @DELETE
    @Path("/{id}")
    public void delete(@PathParam("id") Long id) {
        this.orderService.delete(id);
    }
    @GET
    @Path("/exists/{id}")
    public boolean existsById(@PathParam("id") Long id) {
        return this.orderService.existsById(id);
    }
}
