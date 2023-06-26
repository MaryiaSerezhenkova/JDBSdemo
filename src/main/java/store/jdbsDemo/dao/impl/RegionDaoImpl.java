package store.jdbsDemo.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.zaxxer.hikari.HikariDataSource;

import store.jdbsDemo.dao.RegionDao;
import store.jdbsDemo.dbo.HikariCPDataSource;
import store.jdbsDemo.domain.entity.Product;
import store.jdbsDemo.domain.entity.Region;

public class RegionDaoImpl implements RegionDao {

	private static final String INSERT_SQL = "INSERT INTO app.region (dt_create, dt_update, name) values  (?, ?, ?);";

	private static final String SELECT_BY_ID_SQL = "Select * from app.region where id=?;";

	private static final String SELECT_SQL = "Select * from app.region;";

	private static final String SELECT_ALL_PRODUCTS = "SELECT region.id as region_id, region.name as region_name, region.dt_create as region_create,"
		      + " region.dt_update as region_update, product.id as product_id, product.name as product_name, product.price as product_price," +
		      " product.category as product_category, product.dt_create as product_create, product.dt_update as product_update FROM app.product as product"
		      + " JOIN app.product_region ON product.id = product_region.product_id JOIN app.region ON region.id = product_region.region_id;";
	private static final String INSERT_STORE_SQL = "INSERT INTO app.product_region( " + "	product_id, region_id) "
			+ "	VALUES (?, ?);";
	private static final String SELECT_BY_REGION = "SELECT product.id as product_id, product.name as product_name, product.price as product_price, " +
		      "product.category as product_category, product.dt_create as product_create, product.dt_update as product_update " +
		      "FROM app.product as product JOIN app.product_region AS pr ON product.id = pr.product_id WHERE pr.region_id = ?;";


	public RegionDaoImpl(HikariDataSource instance) {
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

	public List<Product> getByRegion(long regionId) {
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
		return Collections.emptyList();
	}

	public List<Region> getStore() {
		try {
			Connection o = getConnection();
			PreparedStatement s = o.prepareStatement(SELECT_ALL_PRODUCTS);
			List<Region> list = new ArrayList<Region>();
			Map<Long, Region> regionMap = new HashMap<>();
			ResultSet rs = s.executeQuery();
			while (rs.next()) {
				Long regionId = rs.getLong("region_id");
				if (!regionMap.containsKey(regionId)) {
					Region r = new Region();
					r.setId(regionId);
					r.setName(rs.getString("region_name"));
					r.setDtCreate(rs.getTimestamp("region_create").toLocalDateTime());
					r.setDtUpdate(rs.getTimestamp("region_update").toLocalDateTime());
					regionMap.put(regionId, r);
				}

				Product p = new Product();
				p.setId(rs.getLong("product_id"));
				p.setName(rs.getString("product_name"));
				p.setPrice(rs.getDouble("product_price"));
				p.setCategoryId(rs.getLong("product_category"));
				p.setDtCreate(rs.getTimestamp("product_create").toLocalDateTime());
				p.setDtUpdate(rs.getTimestamp("product_update").toLocalDateTime());
				regionMap.get(regionId).addProduct(p);

			}

			return new ArrayList<>(regionMap.values());

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return Collections.emptyList();
	}

	public void createStore(long productId, long regionId) {
		try {
			Connection o = getConnection();
			PreparedStatement s = o.prepareStatement(INSERT_STORE_SQL, Statement.RETURN_GENERATED_KEYS);
			s.setLong(1, productId);
			s.setLong(2, regionId);
			;

			int countUpdatedRows = s.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Product> getProductsByRegion(long regionId) {
		try {
			Connection o = getConnection();
			PreparedStatement s = o.prepareStatement(SELECT_BY_REGION);
			s.setLong(1, regionId);
			ResultSet rs = s.executeQuery();
			List<Product> products = new ArrayList<>();
			while (rs.next()) {

				Product p = new Product();
				p.setId(rs.getLong("product_id"));
				p.setName(rs.getString("product_name"));
				p.setPrice(rs.getDouble("product_price"));
				p.setCategoryId(rs.getLong("product_category"));
				p.setDtCreate(rs.getTimestamp("product_create").toLocalDateTime());
				p.setDtUpdate(rs.getTimestamp("product_update").toLocalDateTime());
				products.add(p);
			}
			return products;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return Collections.emptyList();

	}

}
