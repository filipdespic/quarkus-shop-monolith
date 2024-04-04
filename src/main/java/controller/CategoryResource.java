package controller;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import service.CategoryService;
import service.dtos.CategoryDto;
import service.dtos.ProductDto;

import java.util.List;

@Path("/categories")
public class CategoryResource {
    @Inject
    CategoryService categoryService;
    @GET
    public List<CategoryDto> findAll() {
        return this.categoryService.findAll();
    }
    @GET @Path("/{id}")
    public CategoryDto findById(@PathParam("id") Long id) {
        return this.categoryService.findById(id);
    }
    @GET @Path("/{id}/products")
    public List<ProductDto> findProductsByCategoryId(@PathParam("id") Long id) {
        return this.categoryService.findProductsByCategoryId(id);
    }
    @POST
    public CategoryDto create(CategoryDto categoryDto) {
        return this.categoryService.create(categoryDto);
    }
    @DELETE
    @Path("/{id}")
    public void delete(@PathParam("id") Long id) {
        this.categoryService.delete(id);
    }
}
