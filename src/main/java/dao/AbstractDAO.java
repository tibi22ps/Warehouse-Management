package dao;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import bll.ProdusBLL;
import connection.ConnectionFactory;
import model.Client;
import model.Comanda;
import model.Produs;

/**
 * Poponet Tiberiu-Sergiu, Grupa 30221, An2 UTCN AC Calculatoare
 */
public class AbstractDAO<T> {
	protected static final Logger LOGGER = Logger.getLogger(AbstractDAO.class.getName());

	private  Class<T> type;

	@SuppressWarnings("unchecked")
	public AbstractDAO() {
		this.type = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}
	public AbstractDAO(int a)
	{
	}
	public List<T> findAll() {
		// TODO:
		return null;
	}

	public <T> T findById(int id, Class<T> objectType) {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT * FROM ").append(objectType.getSimpleName());
		sb.append(" WHERE id = ?");
		T object = null;

		try {
			connection = ConnectionFactory.getConnection();
			statement = connection.prepareStatement(sb.toString());
			statement.setInt(1, id);
			resultSet = statement.executeQuery();

			if (resultSet.next()) {
				object = mapResultSetToObject(resultSet, objectType);
			}
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, objectType.getName() + "DAO:findById " + e.getMessage());
		} finally {
			ConnectionFactory.close(resultSet);
			ConnectionFactory.close(statement);
			ConnectionFactory.close(connection);
		}

		return object;
	}

	private <T> T mapResultSetToObject(ResultSet resultSet, Class<T> objectType) throws SQLException {
		T object = null;

		try {
			object = objectType.getDeclaredConstructor().newInstance();

			ResultSetMetaData metaData = resultSet.getMetaData();
			int columnCount = metaData.getColumnCount();

			for (int i = 1; i <= columnCount; i++) {
				String columnName = metaData.getColumnName(i);
				Field field = getFieldByName(objectType, columnName);
				field.setAccessible(true);

				Object value = resultSet.getObject(i);
				field.set(object, value);
			}
		} catch (Exception e) {
			throw new SQLException("Error mapping ResultSet to object: " + e.getMessage());
		}

		return object;
	}

	private Field getFieldByName(Class<?> objectType, String fieldName) {
		try {
			return objectType.getDeclaredField(fieldName);
		} catch (NoSuchFieldException e) {
			// If the field is not found in the current class, check the superclass
			if (objectType.getSuperclass() != null) {
				return getFieldByName(objectType.getSuperclass(), fieldName);
			}
			throw new IllegalArgumentException("Field not found: " + fieldName);
		}
	}
	public T update(T t) {
		// TODO:
		return t;
	}

	public <T> T insert(T t) throws IllegalAccessException {
		Connection connection = null;
		PreparedStatement statement = null;
		String tableName = t.getClass().getSimpleName();
		String query = generateInsertQuery(t);

		try {
			connection = ConnectionFactory.getConnection();
			statement = connection.prepareStatement(query);

			int i = 1;
			for (Field field : t.getClass().getDeclaredFields()) {
				field.setAccessible(true);
				Object value = field.get(t);
				statement.setObject(i, value);
				i++;
			}

			statement.executeUpdate();
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, t.getClass().getName() + "DAO:insert " + e.getMessage());
		} finally {
			ConnectionFactory.close(statement);
			ConnectionFactory.close(connection);
		}

		return t;
	}

	private <T> String generateInsertQuery(T t) {
		String tableName = t.getClass().getSimpleName();
		StringBuilder queryBuilder = new StringBuilder("INSERT INTO ");
		queryBuilder.append(tableName).append(" (");

		Field[] fields = t.getClass().getDeclaredFields();
		for (int i = 0; i < fields.length; i++) {
			fields[i].setAccessible(true);
			queryBuilder.append(fields[i].getName());
			if (i < fields.length - 1) {
				queryBuilder.append(", ");
			}
		}
		queryBuilder.append(") VALUES (");
		for (int i = 0; i < fields.length; i++) {
			queryBuilder.append("?");
			if (i < fields.length - 1) {
				queryBuilder.append(", ");}}
		queryBuilder.append(")");
		return queryBuilder.toString();}

	public <T> T update(T t, int id) throws IllegalAccessException {
		Connection connection = null;
		PreparedStatement statement = null;
		String tableName = t.getClass().getSimpleName();
		String query = generateUpdateQuery(t);
		try {
			connection = ConnectionFactory.getConnection();
			statement = connection.prepareStatement(query);

			int i = 1;
			for (Field field : t.getClass().getDeclaredFields()) {
				field.setAccessible(true);
				if (field.get(t) != null && !field.get(t).equals(-1)) {
					Object value = field.get(t);
					statement.setObject(i, value);
					i++;
				}
			}
			statement.setObject(i, id);
			statement.executeUpdate();
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, t.getClass().getName() + "DAO:update " + e.getMessage());
		} finally {
			ConnectionFactory.close(statement);
			ConnectionFactory.close(connection);}
		return t;}
	private <T> String generateUpdateQuery(T t) {
		String tableName = t.getClass().getSimpleName();
		StringBuilder queryBuilder = new StringBuilder("UPDATE ");
		queryBuilder.append(tableName).append(" SET ");

		Field[] fields = t.getClass().getDeclaredFields();
		for (int i = 0; i < fields.length; i++) {
			fields[i].setAccessible(true);
			queryBuilder.append(fields[i].getName()).append(" = ?");
			if (i < fields.length - 1) {
				queryBuilder.append(", ");}}
		queryBuilder.append(" WHERE id = ?");
		return queryBuilder.toString();}
	public <T> T delete(T t, int id) throws IllegalAccessException {
		Connection connection = null;
		PreparedStatement statement = null;
		String tableName = t.getClass().getSimpleName();
		String query = generateDeleteQuery(t);
		try {
			connection = ConnectionFactory.getConnection();
			statement = connection.prepareStatement(query);

			statement.setObject(1, id);
			statement.executeUpdate();
			return null;
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, t.getClass().getName() + "DAO:delete " + e.getMessage());
		} finally {
			ConnectionFactory.close(statement);
			ConnectionFactory.close(connection);}
		return t;}
	private <T> String generateDeleteQuery(T t) {
		String tableName = t.getClass().getSimpleName();
		StringBuilder queryBuilder = new StringBuilder("DELETE FROM ");
		queryBuilder.append(tableName).append(" WHERE id = ?");
		return queryBuilder.toString();}
	public static String[][] retrieveTableContents(Object object) {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		String className = object.getClass().getSimpleName();
		String[][] tableContents = null;
		try {
			connection = ConnectionFactory.getConnection();
			String query = "SELECT * FROM " + className;
			statement = connection.prepareStatement(query);
			resultSet = statement.executeQuery();
			ResultSetMetaData metaData = resultSet.getMetaData();
			int columnCount = metaData.getColumnCount();
			List<String[]> rows = new ArrayList<>();
			while (resultSet.next()) {
				String[] row = new String[columnCount];
				for (int i = 1; i <= columnCount; i++) {
					row[i - 1] = resultSet.getString(i);}
				rows.add(row);}
			tableContents = new String[rows.size()][columnCount];
			for (int i = 0; i < rows.size(); i++) {
				tableContents[i] = rows.get(i);}
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "Error occurred while retrieving table contents: " + e.getMessage());
		} finally {
			ConnectionFactory.close(resultSet);
			ConnectionFactory.close(statement);
			ConnectionFactory.close(connection);}
		return tableContents;}

	public void generateTable(List<?> objects) {
		if (objects.isEmpty()) {
			System.out.println("No objects in the list.");
			return;}
		Class<?> objectType = objects.get(0).getClass();
		Field[] fields = objectType.getDeclaredFields();
		// Generate table header
		StringBuilder headerBuilder = new StringBuilder("| ");
		for (Field field : fields) {
			headerBuilder.append(field.getName()).append(" | ");}
		String tableHeader = headerBuilder.toString();
		System.out.println(tableHeader);
		// Generate separator line
		int tableWidth = tableHeader.length();
		String separator = "-".repeat(tableWidth);
		System.out.println(separator);
		// Populate table with object values
		for (Object obj : objects) {
			StringBuilder rowBuilder = new StringBuilder("| ");
			for (Field field : fields) {
				field.setAccessible(true);
				try {
					Object value = field.get(obj);
					rowBuilder.append(value).append(" | ");
				} catch (IllegalAccessException e) {
					System.out.println("Error accessing field: " + field.getName());}}
			String tableRow = rowBuilder.toString();
			System.out.println(tableRow);}}}
