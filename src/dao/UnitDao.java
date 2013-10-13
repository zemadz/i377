package dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import models.Unit;

public class UnitDao extends AbstractDao {

	public List<Unit> getUnits() {
		List<Unit> result = new ArrayList<Unit>();
		String queryString = "SELECT ID, NAME, CODE FROM UNIT;";
		try {
			st = getConnection().createStatement();
			rs = st.executeQuery(queryString);
			while (rs.next()) {
				Unit newUnit = new Unit();
				newUnit.setId(rs.getLong(1));
				newUnit.setName(rs.getString(2));
				newUnit.setCode(rs.getString(3));
				result.add(newUnit);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeResources();
		}
		return result;
	}

	public boolean deleteUnit(long id) {
		boolean result = false;
		String queryString = "DELETE FROM UNIT WHERE ID = ?;";
		try {
			pst = getConnection().prepareStatement(queryString);
			pst.setLong(1, id);
			if (pst.execute() && pst.getUpdateCount() == 1) {
				result = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeResources();
		}
		return result;
	}

	public List<Unit> findUnitsThatNameContains(String searchString) {
		List<Unit> result = new ArrayList<Unit>();
		String queryString = "SELECT ID, NAME, CODE FROM UNIT WHERE LCASE(NAME) LIKE ?;";
		try {
			pst = getConnection().prepareStatement(queryString);
			pst.setString(1, "%" + searchString.toLowerCase() + "%");
			rs = pst.executeQuery();
			while (rs.next()) {
				Unit newUnit = new Unit();
				newUnit.setId(rs.getLong(1));
				newUnit.setName(rs.getString(2));
				newUnit.setCode(rs.getString(3));
				result.add(newUnit);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeResources();
		}
		return result;
	}

	public void clearTable() {
		String queryString = "DELETE * FROM UNIT";
		try {
			getConnection().createStatement().executeQuery("TRUNCATE SCHEMA public AND COMMIT");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeResources();
		}
	}

	public boolean addUnit(Unit unit) {
		boolean result = false;
		String queryString = "INSERT INTO UNIT (ID,NAME,CODE) VALUES (NEXT VALUE FOR seq1,?,?)";
		try {
			pst = getConnection().prepareStatement(queryString);
			pst.setString(1, unit.getName());
			pst.setString(2, unit.getCode());
			if (pst.execute() && pst.getUpdateCount() == 1) {
				result = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeResources();
		}
		return result;
	}
}
