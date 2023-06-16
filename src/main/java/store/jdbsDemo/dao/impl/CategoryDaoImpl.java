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

import store.jdbsDemo.dao.CategoryDao;
import store.jdbsDemo.dbo.HikariCPDataSource;
import store.jdbsDemo.domain.entity.Category;
import store.jdbsDemo.domain.entity.Product;

public class CategoryDaoImpl implements CategoryDao {

	private static final String INSERT_SQL = "INSERT INTO app.category (dt_create, dt_update, name) values  (?, ?, ?);";

	private static final String SELECT_BY_ID_SQL = "Select * from app.category where id=?;";

	private static final String SELECT_SQL = "Select * from app.category;";

	public CategoryDaoImpl(HikariDataSource instance) {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Category create(Category c) {
		try {
			Connection o = getConnection();
			PreparedStatement s = o.prepareStatement(INSERT_SQL, Statement.RETURN_GENERATED_KEYS);
			s.setObject(1, c.getDtCreate());
			s.setObject(2, c.getDtUpdate());
			s.setString(3, c.getName());
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
	public Category get(long id) {
		try {
			Connection o = getConnection();
			PreparedStatement s = o.prepareStatement(SELECT_BY_ID_SQL);
			s.setLong(1, id);
			ResultSet rs = s.executeQuery();
			if (rs.next()) {
				Category c = new Category();
				c.setId(rs.getLong("id"));
				c.setName(rs.getString("name"));
				c.setDtCreate(rs.getTimestamp("dt_create").toLocalDateTime());
				c.setDtUpdate(rs.getTimestamp("dt_update").toLocalDateTime());
				return c;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Category> getAll() {
		try {
			Connection o = getConnection();
			Statement s = o.createStatement();
			ResultSet rs = s.executeQuery(SELECT_SQL);
			List<Category> list = new ArrayList<Category>();
			while (rs.next()) {
				Category c = new Category();
				c.setId(rs.getLong("id"));
				c.setName(rs.getString("name"));
				c.setDtCreate(rs.getTimestamp("dt_create").toLocalDateTime());
				c.setDtUpdate(rs.getTimestamp("dt_update").toLocalDateTime());
				list.add(c);
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
