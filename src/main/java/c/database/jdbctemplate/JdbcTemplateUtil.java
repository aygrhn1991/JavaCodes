/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package c.database.jdbctemplate;

import c.database.models.JdbcDataModel;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import oracle.jdbc.OracleTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.CallableStatementCallback;
import org.springframework.jdbc.core.CallableStatementCreator;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Administrator
 */
@Repository("jdbcTemplateUtil")
public class JdbcTemplateUtil {

    @Autowired
    @Qualifier("jdbcTemplateWithoutPool")
    private JdbcTemplate jdbcTemplateWithoutPool;

    @Autowired
    @Qualifier("jdbcTemplateWithPool")
    private JdbcTemplate jdbcTemplateWithPool;

    public List<JdbcDataModel> getJdbcDataModelList() {
        List resultList = (List) this.jdbcTemplateWithoutPool.execute(
                new CallableStatementCreator() {
            @Override
            public CallableStatement createCallableStatement(Connection connection) throws SQLException {
                String procedure = "{call package.procedure(?,?,?)}";
                CallableStatement cs = connection.prepareCall(procedure);
                cs.setString(1, "param_1");
                cs.setString(2, "param_2");
                cs.registerOutParameter(3, OracleTypes.CURSOR);
                return cs;
            }
        }, new CallableStatementCallback() {
            @Override
            public Object doInCallableStatement(CallableStatement cs) throws SQLException {
                cs.execute();
                ResultSet rs = (ResultSet) cs.getObject(2);
                List<JdbcDataModel> list = new ArrayList();
                while (rs.next()) {
                    JdbcDataModel model = new JdbcDataModel(
                            rs.getInt("col_1"),
                            rs.getString("col_2")
                    );
                    list.add(model);
                }
                rs.close();
                return list;
            }
        });
        return resultList;
    }
}
