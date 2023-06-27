package store;

import java.util.List;
import java.util.stream.IntStream;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import store.jdbsDemo.domain.entity.Category;
import store.jdbsDemo.service.CategoryService;
import store.jdbsDemo.service.impl.CategoryServiceSingleton;

public class CategoryServiceImplTest {
	private final CategoryService categoryService = CategoryServiceSingleton.getInstance();
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
        List<Category> categories = categoryService.get();
        Assertions.assertThat(categories).isNotEmpty();
    }

    @Test
    void read() {
        testDataBuilder.fillTables();
        IntStream.rangeClosed(1,3).forEach(value -> Assertions.assertThat(
                categoryService.read(value))
                .isInstanceOf(Category.class));
    }

}
