package store.jdbsDemo.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.zaxxer.hikari.HikariDataSource;

import store.jdbsDemo.dao.ProductDao;
import store.jdbsDemo.dbo.HikariCPDataSource;
import store.jdbsDemo.domain.entity.Product;

public class ProductDaoImpl implements ProductDao {

	private static final String INSERT_SQL = "INSERT INTO app.product (dt_create, dt_update, name, price, category) values (?, ?, ?, ?, ?);";

	private static final String SELECT_BY_ID_SQL = "Select * from app.product where id=?;";

	private static final String SELECT_SQL = "Select * from app.product;";

	private static final String UPDATE_SQL = "UPDATE app.product\r\n"
			+ "	SET dt_update=?, name=?, price=?, category=?\r\n"
			+ "\tWHERE id = ? and dt_update = ?;";

	private static final String DELETE_SQL = "DELETE FROM app.product WHERE id = ? and dt_update = ?;";
	
	private static final String SELECT_PRODUCTS_BY_CATEGORY_ID_SQL = "SELECT product.id, product.name, product.price, product.category, product.dt_create, product.dt_update\n"
			+ "	FROM app.product JOIN app.category ON product.category=category.id WHERE category=?;";

	public ProductDaoImpl(HikariDataSource instance) {
		// TODO Auto-generated constructor stub
	}

	public ProductDaoImpl() {
		// TODO Auto-generated constructor stub
	}

	public Product create(Product p) {
		try {
			Connection o = getConnection();
			PreparedStatement s = o.prepareStatement(INSERT_SQL, Statement.RETURN_GENERATED_KEYS);
			s.setObject(1, p.getDtCreate());
			s.setObject(2, p.getDtUpdate());
			s.setString(3, p.getName());
			s.setDouble(4, p.getPrice());
			s.setLong(5, p.getCategoryId());
			int updated = s.executeUpdate();
			ResultSet rs = s.getGeneratedKeys();
			if (rs.next()) {
				return get(rs.getLong(1));
			}
			return null;
		} catch (SQLException e) {
			throw new RuntimeException("При сохранении данных произошла ошибка", e);
		}
	}

	

	public Product get(long id) {
		try {
			Connection o = getConnection();
			PreparedStatement s = o.prepareStatement(SELECT_BY_ID_SQL);
			s.setLong(1, id);
			ResultSet rs = s.executeQuery();
			if (rs.next()) {
				Product p = new Product();
				p.setId(rs.getLong("id"));
				p.setName(rs.getString("name"));
				p.setPrice(rs.getDouble("price"));
				p.setCategoryId(rs.getLong("category"));
				p.setDtCreate(rs.getTimestamp("dt_create").toLocalDateTime());
				p.setDtUpdate(rs.getTimestamp("dt_update").toLocalDateTime());
				return p;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public List<Product> getAll() {
		try {
			Connection o = getConnection();
			Statement s = o.createStatement();
			ResultSet rs = s.executeQuery(SELECT_SQL);
			List<Product> list = new ArrayList<Product>();
			while (rs.next()) {
				Product p = new Product();
				p.setId(rs.getLong("id"));
				p.setName(rs.getString("name"));
				p.setPrice(rs.getDouble("price"));
				p.setCategoryId(rs.getLong("category"));
				p.setDtCreate(rs.getTimestamp("dt_create").toLocalDateTime());
				p.setDtUpdate(rs.getTimestamp("dt_update").toLocalDateTime());
				list.add(p);
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return Collections.emptyList();
	}
	public List<Product> getByCategory(long categoryId) {
		try {
			Connection o = getConnection();
			PreparedStatement s = o.prepareStatement(SELECT_PRODUCTS_BY_CATEGORY_ID_SQL);
			s.setLong(1, categoryId);
			ResultSet rs = s.executeQuery();
			List<Product> list = new ArrayList<Product>();
			while (rs.next()) {
				Product p = new Product();
				p.setId(rs.getLong("id"));
				p.setName(rs.getString("name"));
				p.setPrice(rs.getDouble("price"));
				p.setCategoryId(rs.getLong("category"));
				p.setDtCreate(rs.getTimestamp("dt_create").toLocalDateTime());
				p.setDtUpdate(rs.getTimestamp("dt_update").toLocalDateTime());
				list.add(p);
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return Collections.emptyList();
	}


	private Connection getConnection() throws SQLException {
		return HikariCPDataSource.getConnection();
	}

	public void delete(long id, LocalDateTime dtUpdate) {
		try {
			Connection o = getConnection();
			PreparedStatement s = o.prepareStatement(DELETE_SQL, Statement.RETURN_GENERATED_KEYS);
			s.setLong(1, id);
			s.setObject(2, dtUpdate);

			int countUpdatedRows = s.executeUpdate();

			if (countUpdatedRows != 1) {
				if (countUpdatedRows == 0) {
					throw new IllegalArgumentException("Не смогли удалить какую либо запись");
				} else {
					throw new IllegalArgumentException("Удалили более одной записи");
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException("При сохранении данных произошла ошибка", e);
		}
	}

	@Override
	public Product update(long id, LocalDateTime dtUpdate, Product p) {
		try {
			Connection o = getConnection();
			PreparedStatement s = o.prepareStatement(UPDATE_SQL, Statement.RETURN_GENERATED_KEYS);
			s.setObject(1, p.getDtUpdate());
			s.setString(2, p.getName());
			s.setDouble(3, p.getPrice());
			s.setLong(4, p.getCategoryId());
			s.setLong(5, id);
			s.setObject(6, dtUpdate);

			int countUpdatedRows = s.executeUpdate();

			if (countUpdatedRows != 1) {
				if (countUpdatedRows == 0) {
					throw new IllegalArgumentException("Не смогли обновить какую либо запись");
				} else {
					throw new IllegalArgumentException("Обновили более одной записи");
				}
			}

			return get(id);
		} catch (SQLException e) {
			throw new RuntimeException("При сохранении данных произошла ошибка", e);
		}
	}
	
}
