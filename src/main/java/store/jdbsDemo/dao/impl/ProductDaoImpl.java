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

	private static final String INSERT_SQL = "INSERT INTO app.product id, dt_create, dt_update, name, price, category (?, ?, ?, ?, ?);";

	private static final String SELECT_BY_ID_SQL = "Select * from app.product where id=?;";

	private static final String SELECT_SQL = "Select * from app.product;";

	private static final String UPDATE_SQL = "UPDATE app.product\r\n"
			+ "	SET id=?, dt_create=?, dt_update=?, name=?, price=?, catrgory=?\r\n"
			+ "\tWHERE id = ? and dt_update = ?;";

	private static final String DELETE_SQL = "DELETE FROM app.product WHERE id = ? and dt_update = ?;";

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
			s.setString(1, p.getName());
			s.setDouble(2, p.getPrice());
			s.setLong(3, p.getCategoryId());
			s.setObject(4, p.getDtCreate());
			s.setObject(5, p.getDtUpdate());
			
			int updated = s.executeUpdate();
			return get(s.getGeneratedKeys().getLong(1));
		} catch (SQLException e) {
			throw new RuntimeException("При сохранении данных произошла ошибка", e);
		}
	}

	public Product update(Product p) {
		// TODO Auto-generated method stub
		return null;
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
}
