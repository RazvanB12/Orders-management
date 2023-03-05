package dao;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import connection.ConnectionFactory;

/**
 *	Aceasta clasa este responsabila pentru crearea de interogari asupra bazei de date
 *	Metoda createSelectQuery - creeaza interogare pentru selectia unui camp din tabel
 *	Metoda createInsertQuery - creeaza interogare pentru insertia unui camp in tabel
 *	Metoda createDeleteQuery - creeaza interogare pentru stergerea unui camp din tabel
 *	Metoda createUpdateQuery - creeaza interogare pentru actualizarea unui camp din tabel
 *	Metoda createSelectAllQuery - creeaza interogare pentru selectia tuturor campurilor din tabel
 *	Metoda findById - realizeaza returnarea unui obiect dupa id
 *	Metoda insert - realizeaza inserarea unui obiect in tabel
 *	Metoda delete - realizeaza stergerea unui obiect din tabel
 *	Metoda update - realizeaza actualizarea unui obiect din tabel
 *	Metoda findAll - realizeaza returnarea tuturor obiectelor din tabel
 */

public class AbstractDAO<T> {
	protected static final Logger LOGGER = Logger.getLogger(AbstractDAO.class.getName());

	private final Class<T> type;

	@SuppressWarnings("unchecked")
	public AbstractDAO() {
		this.type = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}

	private String createSelectQuery(String field) {
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT * FROM ");
		sb.append(type.getSimpleName());
		sb.append(" WHERE " + field + " = ?" + ";");
		System.out.println(sb.toString());
		return sb.toString();
	}

	private String createInsertQuery(String values) {
		StringBuilder sb = new StringBuilder();
		StringBuilder field = new StringBuilder();
		sb.append("INSERT INTO ");
		sb.append(type.getSimpleName());
		for(Field fields: type.getDeclaredFields()) {
			if (field.length() > 0){
				field.append(", ");
			}
			if (fields.getName() != "id"){
				field.append(fields.getName());
			}
		}
		sb.append(" (" + field + ")" + " VALUES (" + values + ");");
		System.out.println(sb.toString());
		return sb.toString();
	}

	private String createDeleteQuery(String field) {
		StringBuilder sb = new StringBuilder();
		sb.append("DELETE FROM ");
		sb.append(type.getSimpleName());
		sb.append(" WHERE " + field + " = ?" + ";");
		System.out.println(sb.toString());
		return sb.toString();
	}

	private String createUpdateQuery(int id, String values) {
		StringBuilder sb = new StringBuilder();
		StringBuilder field = new StringBuilder();
		String[] splitted = values.split(", ");
		sb.append("UPDATE ");
		sb.append(type.getSimpleName());
		sb.append(" SET ");
		int i = 0;
		for(Field fields: type.getDeclaredFields()) {
			if (field.length() > 0){
				field.append(", ");
			}
			if (fields.getName() != "id"){
				field.append(fields.getName() + " = ");
				field.append("'" + splitted[i] + "'");
				i++;
			}
		}
		sb.append(field + " WHERE id = '" + id + "';");
		System.out.println(sb.toString());
		return sb.toString();
	}

	private String createSelectAllQuery() {
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT * FROM ");
		sb.append(type.getSimpleName());
		System.out.println(sb.toString());
		return sb.toString();
	}

	public T findById(int id) {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		String query = createSelectQuery("id");
		try {
			connection = ConnectionFactory.getConnection();
			statement = connection.prepareStatement(query);
			statement.setInt(1, id);
			resultSet = statement.executeQuery();
			return createObjects(resultSet).get(0);
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, type.getName() + "DAO:findById " + e.getMessage());
		} finally {
			ConnectionFactory.close(resultSet);
			ConnectionFactory.close(statement);
			ConnectionFactory.close(connection);
		}
		return null;
	}

	public T insert(String field) {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		String query = createInsertQuery(field);
		try {
			connection = ConnectionFactory.getConnection();
			statement = connection.prepareStatement(query);
			statement.executeUpdate();
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, type.getName() + "DAO:insert " + e.getMessage());
		} finally {
			ConnectionFactory.close(resultSet);
			ConnectionFactory.close(statement);
			ConnectionFactory.close(connection);
		}
		return null;
	}

	public void delete(int id) {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		String query = createDeleteQuery("id");
		try {
			connection = ConnectionFactory.getConnection();
			statement = connection.prepareStatement(query);
			statement.setInt(1, id);
			System.out.println(statement.toString());
			statement.executeUpdate();

		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, type.getName() + "DAO:delete " + e.getMessage());
		} finally {
			ConnectionFactory.close(resultSet);
			ConnectionFactory.close(statement);
			ConnectionFactory.close(connection);
		}
	}

	public T update(int id, String field) {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		String query = createUpdateQuery(id, field);
		try {
			connection = ConnectionFactory.getConnection();
			statement = connection.prepareStatement(query);
			statement.executeUpdate();
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, type.getName() + "DAO:update " + e.getMessage());
		} finally {
			ConnectionFactory.close(resultSet);
			ConnectionFactory.close(statement);
			ConnectionFactory.close(connection);
		}
		return null;
	}

	public List<T> findAll() {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		String query = createSelectAllQuery();
		try {
			connection = ConnectionFactory.getConnection();
			statement = connection.prepareStatement(query);
			resultSet = statement.executeQuery();
			return createObjects(resultSet);
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, type.getName() + "DAO:findById " + e.getMessage());
		} finally {
			ConnectionFactory.close(resultSet);
			ConnectionFactory.close(statement);
			ConnectionFactory.close(connection);
		}
		return null;
	}

	private List<T> createObjects(ResultSet resultSet) {
		List<T> list = new ArrayList<T>();
		Constructor[] ctors = type.getDeclaredConstructors();
		Constructor ctor = null;
		for (int i = 0; i < ctors.length; i++) {
			ctor = ctors[i];
			if (ctor.getGenericParameterTypes().length == 0)
				break;
		}
		try {
			while (resultSet.next()) {
				ctor.setAccessible(true);
				T instance = (T)ctor.newInstance();
				for (Field field : type.getDeclaredFields()) {
					String fieldName = field.getName();
					Object value = resultSet.getObject(fieldName);
					PropertyDescriptor propertyDescriptor = new PropertyDescriptor(fieldName, type);
					Method method = propertyDescriptor.getWriteMethod();
					method.invoke(instance, value);
				}
				list.add(instance);
			}
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IntrospectionException e) {
			e.printStackTrace();
		}
		return list;
	}
}
