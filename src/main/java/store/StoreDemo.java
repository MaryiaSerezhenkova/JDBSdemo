package store;

import store.jdbsDemo.dao.ProductDao;
import store.jdbsDemo.dao.impl.ProductDaoImpl;
import store.jdbsDemo.domain.entity.Product;

public class StoreDemo {

	public static void main(String[] args) {

		ProductDao dao = new ProductDaoImpl();
//		for (Product p:dao.getAll()) {
//			System.out.println(p);
//		}
		System.out.println(dao.get(1));
		}
	
}
