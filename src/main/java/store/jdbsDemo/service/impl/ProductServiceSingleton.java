package store.jdbsDemo.service.impl;

import store.jdbsDemo.dao.impl.ProductDaoSingleton;
import store.jdbsDemo.service.ProductService;

public class ProductServiceSingleton {
    private final ProductService productService;
    private volatile static ProductServiceSingleton firstInstance = null;

    public ProductServiceSingleton() {
        this.productService = new ProductServiceImpl(ProductDaoSingleton.getInstance());
    }

    public static ProductService getInstance() {
        synchronized (ProductServiceSingleton.class) {
            if (firstInstance == null) {
                firstInstance = new ProductServiceSingleton();
            }
        }
        return firstInstance.productService;
    }
}