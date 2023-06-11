package store.jdbsDemo.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import store.jdbsDemo.dao.ProductDao;
import store.jdbsDemo.dbo.HikariCPDataSource;
import store.jdbsDemo.domain.entity.Product;

public class ProductDaoImpl implements ProductDao {

	@Override
	public long create(Product p) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long update(Product p) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void delete(long id) {
		// TODO Auto-generated method stub

	}

	@Override
	public Product get(long id) {
		try {
			Connection o = getConnection();
			PreparedStatement s = o.prepareStatement("Select * from app.product where id=?");
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

	@Override
	public List<Product> getAll() {
		try {
			Connection o = getConnection();
			Statement s = o.createStatement();
			ResultSet rs = s.executeQuery("Select * from app.product");
			List<Product> list = new ArrayList<>();
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

}
