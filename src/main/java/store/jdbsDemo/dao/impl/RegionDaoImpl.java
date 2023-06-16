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
import store.jdbsDemo.domain.entity.Region;

public class RegionDaoImpl implements RegionDao {

	private static final String INSERT_SQL = "INSERT INTO app.region (dt_create, dt_update, name) values  (?, ?, ?);";

	private static final String SELECT_BY_ID_SQL = "Select * from app.region where id=?;";

	private static final String SELECT_SQL = "Select * from app.region;";

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

}
