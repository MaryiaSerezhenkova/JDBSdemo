package store;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.stream.IntStream;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import store.jdbsDemo.domain.entity.Product;
import store.jdbsDemo.domain.entity.Region;
import store.jdbsDemo.service.RegionService;
import store.jdbsDemo.service.impl.RegionServiceSingleton;

public class RegionServiceImplTest {
	
	private final RegionService regionService = RegionServiceSingleton.getInstance();
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
        List<Region> regions = regionService.get();
        Assertions.assertThat(regions).isNotEmpty();
    }

    @Test
    void read() {
        testDataBuilder.fillTables();
        IntStream.rangeClosed(1,3).forEach(value -> Assertions.assertThat(
        		regionService.read(value))
                .isInstanceOf(Region.class));
    }
    @Test
    void findProductsByRegion() {
        testDataBuilder.fillTables();
        List<Product> products = regionService.getProductsByRegion(1);
        assertThat(products.stream().findAny().get()).isInstanceOf(Product.class);
    }
    @Test
    void findStore() {
        testDataBuilder.fillTables();
        List<Region> regionList = regionService.getStore();
        assertThat(regionList.stream().findAny().get()).isInstanceOf(Region.class);
    }
}
