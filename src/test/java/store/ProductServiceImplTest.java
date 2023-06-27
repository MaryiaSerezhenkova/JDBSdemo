package store;

import java.util.List;
import java.util.stream.IntStream;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import store.jdbsDemo.domain.entity.Product;
import store.jdbsDemo.domain.entity.dto.ProductDto;
import store.jdbsDemo.service.ProductService;
import store.jdbsDemo.service.impl.ProductServiceSingleton;

public class ProductServiceImplTest {
	private final ProductService productService = ProductServiceSingleton.getInstance();
    private final TestDataBuilder testDataBuilder = new TestDataBuilder();

    @BeforeEach
    void fillTables() {
        testDataBuilder.createTables();
    }

    @AfterEach
    void clearData() {
        testDataBuilder.close();
    }

    @Test
    void get() {
        testDataBuilder.fillTables();
        List<Product> products = productService.get();
        Assertions.assertThat(products).isNotEmpty();
    }

//    @Test
//    void read() {
//        testDataBuilder.fillTables();
//        IntStream.rangeClosed(1, 3).forEach(value -> Assertions.assertThat(
//        		productService.read(value))
//                .isInstanceOf(Product.class));
//    }
    @Test
    void create() {
    	ProductDto testProduct = new ProductDto("name", 10.0, 1);
        Assertions.assertThat(productService.create(testProduct)).isNotNull();
    }
    @Test
    void update() {
        ProductDto product = new ProductDto("nameBefore", 10.0, 1);
        ProductDto product2 = new ProductDto("nameAfter", 10.0, 1);
        Product testProduct = productService.create(product);
        Product testProduct2 = productService.update(testProduct.getId(), testProduct.getDtUpdate(), product2);
        Assertions.assertThat(testProduct2.getName()).isEqualTo("nameAfter");
    }
    @Test
    void getByCategoryId() {
    	 testDataBuilder.fillTables();
         List<Product> products = productService.getByCategory(1);
         Assertions.assertThat(products.stream().findAny().get()).isInstanceOf(Product.class);
    }
}
