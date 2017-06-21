/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package c.database.jdbc;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Administrator
 */
public class JdbcUtil {

    public JdbcUtil(String user, String password, String url, String dirver) {
        this.user = user;
        this.password = password;
        this.url = url;
        this.dirver = dirver;
    }
    private final String user;
    private final String password;
    private final String url;
    private final String dirver;
    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    public Connection openConnection() {
        try {
            Class.forName(dirver);
            connection = DriverManager.getConnection(url, user, password);
        } catch (Exception e) {
            System.out.println(e);
        }
        return connection;
    }

    public void closeConnection() {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                System.out.println(e);
            }
        }
        if (preparedStatement != null) {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                System.out.println(e);
            }
        }
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                System.out.println(e);
            }
        }
    }

    public int update(String sql, Object... params) {
        try {
            preparedStatement = connection.prepareStatement(sql);
            for (int i = 0; i < params.length; i++) {
                preparedStatement.setObject(i + 1, params[i]);
            }
            int changeRows = preparedStatement.executeUpdate();
            return changeRows;
        } catch (Exception e) {
            System.out.println(e);
        }
        return -1;
    }

    public List<Map<String, Object>> queryMapList(String sql, Object... params) {
        try {
            List<Map<String, Object>> list = new ArrayList<>();
            preparedStatement = connection.prepareStatement(sql);
            for (int i = 0; i < params.length; i++) {
                preparedStatement.setObject(i + 1, params[i]);
            }
            resultSet = preparedStatement.executeQuery();
            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();
            while (resultSet.next()) {
                Map<String, Object> map = new HashMap<>();
                for (int i = 0; i < columnCount; i++) {
                    String columnName = metaData.getColumnName(i + 1);
                    Object columnValue = resultSet.getObject(columnName);
                    if (columnValue == null) {
                        columnValue = "";
                    }
                    map.put(columnName, columnValue);
                }
                list.add(map);
            }
            return list;
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    public <T> List<T> queryModelList(String sql, Class<T> modelClass, Object... params) {
        try {
            List<T> list = new ArrayList<>();
            preparedStatement = connection.prepareStatement(sql);
            for (int i = 0; i < params.length; i++) {
                preparedStatement.setObject(i + 1, params[i]);
            }
            resultSet = preparedStatement.executeQuery();
            Field[] fields = modelClass.getDeclaredFields();
            for (Field field : fields) {
                field.setAccessible(true);
            }
            while (resultSet.next()) {
                T t = modelClass.newInstance();
                for (Field field : fields) {
                    String name = field.getName();
                    Object value = resultSet.getObject(name);
                    field.set(t, value);
                }
                list.add(t);
            }
            return list;
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    public <T> List<T> queryObjectList(String sql, Class<T> objectClass, Object... params) {
        try {
            List<T> list = new ArrayList<>();
            preparedStatement = connection.prepareStatement(sql);
            for (int i = 0; i < params.length; i++) {
                preparedStatement.setObject(i + 1, params[i]);
            }
            resultSet = preparedStatement.executeQuery();
            label:
            while (resultSet.next()) {
                Constructor<?>[] constor = objectClass.getConstructors();
                for (Constructor<?> c : constor) {
                    Object value = resultSet.getObject(1);
                    try {
                        T t = (T) c.newInstance(value);
                        list.add(t);
                        continue label;
                    } catch (Exception e) {
                        System.out.println(e);
                    }
                }
            }
            return list;
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }
}
