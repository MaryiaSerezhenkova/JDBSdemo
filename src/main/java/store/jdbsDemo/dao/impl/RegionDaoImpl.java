package store.jdbsDemo.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.zaxxer.hikari.HikariDataSource;

import store.jdbsDemo.dao.RegionDao;
import store.jdbsDemo.dbo.HikariCPDataSource;
import store.jdbsDemo.domain.entity.Category;
import store.jdbsDemo.domain.entity.Product;
import store.jdbsDemo.domain.entity.Region;

public class RegionDaoImpl implements RegionDao {

	private static final String INSERT_SQL = "INSERT INTO app.region (dt_create, dt_update, name) values  (?, ?, ?);";

	private static final String SELECT_BY_ID_SQL = "Select * from app.region where id=?;";

	private static final String SELECT_SQL = "Select * from app.region;";

	private static final String SELECT_ALL_PRODUCTS = "SELECT region.id, region.name, region.dt_create, region.dt_update, product.id, product.name, product.price, product.category, product.dt_create, product.dt_update\r\n"
			+ "	FROM app.product JOIN app.product_region ON product.id=product_region.product_id JOIN app.region ON region.id=product_region.region_id;";
	private static final String SELECT_BY_REGION = "SELECT region.id, product.id, product.name, product.price, product.category, product.dt_create, product.dt_update\r\n"
			+ "	FROM app.product JOIN app.product_region ON product.id=product_region.product_id JOIN app.region ON region.id=product_region.region_id \r\n"
			+ "	WHERE region.id=? ;";

	public RegionDaoImpl(HikariDataSource instance) {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Region create(Region r) {
		try {
			Connection o = getConnection();
			PreparedStatement s = o.prepareStatement(INSERT_SQL, Statement.RETURN_GENERATED_KEYS);
			s.setObject(1, r.getDtCreate());
			s.setObject(2, r.getDtUpdate());
			s.setString(3, r.getName());
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

	@Override
	public Region get(long id) {
		try {
			Connection o = getConnection();
			PreparedStatement s = o.prepareStatement(SELECT_BY_ID_SQL);
			s.setLong(1, id);
			ResultSet rs = s.executeQuery();
			if (rs.next()) {
				Region r = new Region();
				r.setId(rs.getLong("id"));
				r.setName(rs.getString("name"));
				r.setDtCreate(rs.getTimestamp("dt_create").toLocalDateTime());
				r.setDtUpdate(rs.getTimestamp("dt_update").toLocalDateTime());
				return r;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Region> getAll() {
		try {
			Connection o = getConnection();
			Statement s = o.createStatement();
			ResultSet rs = s.executeQuery(SELECT_SQL);
			List<Region> list = new ArrayList<Region>();
			while (rs.next()) {
				Region r = new Region();
				r.setId(rs.getLong("id"));
				r.setName(rs.getString("name"));
				r.setDtCreate(rs.getTimestamp("dt_create").toLocalDateTime());
				r.setDtUpdate(rs.getTimestamp("dt_update").toLocalDateTime());
				list.add(r);
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

	public void getByRegion(long regionId) {
		try {
			Connection o = getConnection();
			PreparedStatement s = o.prepareStatement(SELECT_BY_REGION);
			s.setLong(1, regionId);
			List<Product> list = new ArrayList<Product>();
			ResultSet rs = s.executeQuery();
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
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public List<Product> getStore( ) {
		try {
			Connection o = getConnection();
			PreparedStatement s = o.prepareStatement(SELECT_ALL_PRODUCTS);
			List<Product> list = new ArrayList<Product>();
			ResultSet rs = s.executeQuery();
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

}
