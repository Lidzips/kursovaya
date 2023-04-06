package com.example.demo.repository;

import com.example.demo.entity.Rule;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import java.sql.Types;
import java.util.ArrayList;

@Repository
public class RuleRepository implements IRestRepository<Rule>{
    protected final JdbcOperations jdbcOperations;

    private static String selectQuery = "SELECT \"id\", \"alphabet_from_id\", \"alphabet_to_id\", \"symbol_in_id\", \"symbol_out_id\" " +
            "FROM \"rule\" " +
            "ORDER BY \"id\"";

    private static String selectByIdQuery = "SELECT \"id\", \"alphabet_from_id\", \"alphabet_to_id\", \"symbol_in_id\", \"symbol_out_id\" " +
            "FROM \"rule\" " +
            "WHERE \"id\" = ?";

    private static String selectByAlphabetFromIdQuery = "SELECT \"id\", \"alphabet_from_id\", \"alphabet_to_id\", \"symbol_in_id\", \"symbol_out_id\" " +
            "FROM \"rule\" " +
            "WHERE \"alphabet_from_id\" = ?";

    private static String selectByAlphabetToIdQuery = "SELECT \"id\", \"alphabet_from_id\", \"alphabet_to_id\", \"symbol_in_id\", \"symbol_out_id\" " +
            "FROM \"rule\" " +
            "WHERE \"alphabet_to_id\" = ?";

    private static String insertQuery = "INSERT INTO \"rule\"(\"alphabet_from_id\", \"alphabet_to_id\", \"symbol_in_id\", \"symbol_out_id\")" +
            "VALUES (?, ?, ?, ?) " +
            "RETURNING \"id\", \"alphabet_from_id\", \"alphabet_to_id\", \"symbol_in_id\", \"symbol_out_id\" ";

    private static String updateQuery = "UPDATE \"rule\" " +
            "SET \"alphabet_from_id\" = ?, \"alphabet_to_id\" = ?, \"symbol_in_id\" = ?, \"symbol_out_id\" = ? " +
            "WHERE \"id\" = ? " +
            "RETURNING \"id\", \"alphabet_from_id\", \"alphabet_to_id\", \"symbol_in_id\", \"symbol_out_id\"";

    private static String deleteQuery = "DELETE FROM \"rule\" " +
            "WHERE \"id\" = ? " +
            "RETURNING \"id\", \"alphabet_from_id\", \"alphabet_to_id\", \"symbol_in_id\", \"symbol_out_id\"";

    public RuleRepository(JdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
    }

    @Override
    public Rule[] select() {
        ArrayList<Rule> values = new ArrayList<Rule>();
        SqlRowSet rowSet = jdbcOperations.queryForRowSet(selectQuery);
        while (rowSet.next()) {
            values.add(new Rule(
                    rowSet.getInt(1),
                    rowSet.getInt(2),
                    rowSet.getInt(3),
                    rowSet.getInt(4),
                    rowSet.getInt(5)
            ));
        }
        Rule[] result = new Rule[values.size()];
        result = values.toArray(result);
        return result;
    }

    @Override
    public Rule select(Integer id) {
        Object[] params = new Object[] { id };
        int[] types = new int[] { Types.INTEGER };
        SqlRowSet rowSet = jdbcOperations.queryForRowSet(selectByIdQuery, params, types);
        if (!rowSet.next()) {
            return null;
        }
        return new Rule(
                rowSet.getInt(1),
                rowSet.getInt(2),
                rowSet.getInt(3),
                rowSet.getInt(4),
                rowSet.getInt(5)
        );
    }

    public Rule[] selectByAlphabetFromId(Integer sourceId) {
        ArrayList<Rule> values = new ArrayList<Rule>();
        Object[] params = new Object[] { sourceId };
        int[] types = new int[] { Types.INTEGER };
        SqlRowSet rowSet = jdbcOperations.queryForRowSet(selectByAlphabetFromIdQuery, params, types);
        while (rowSet.next()) {
            values.add(new Rule(
                    rowSet.getInt(1),
                    rowSet.getInt(2),
                    rowSet.getInt(3),
                    rowSet.getInt(4),
                    rowSet.getInt(5)
            ));
        }
        Rule[] result = new Rule[values.size()];
        result = values.toArray(result);
        return result;
    }

    public Rule[] selectByAlphabetToId(Integer sourceId) {
        ArrayList<Rule> values = new ArrayList<Rule>();
        Object[] params = new Object[] { sourceId };
        int[] types = new int[] { Types.INTEGER };
        SqlRowSet rowSet = jdbcOperations.queryForRowSet(selectByAlphabetToIdQuery, params, types);
        while (rowSet.next()) {
            values.add(new Rule(
                    rowSet.getInt(1),
                    rowSet.getInt(2),
                    rowSet.getInt(3),
                    rowSet.getInt(4),
                    rowSet.getInt(5)
            ));
        }
        Rule[] result = new Rule[values.size()];
        result = values.toArray(result);
        return result;
    }

    @Override
    public Rule insert(Rule entity) {
        Object[] params = new Object[] { entity.getAlphabetFromId(), entity.getAlphabetToId(), entity.getSymbolInId(), entity.getSymbolOutId() };
        int[] types = new int[] { Types.INTEGER, Types.INTEGER, Types.INTEGER, Types.INTEGER  };
        SqlRowSet rowSet = jdbcOperations.queryForRowSet(insertQuery, params, types);
        if (!rowSet.next()) {
            return null;
        }
        return new Rule(
                rowSet.getInt(1),
                rowSet.getInt(2),
                rowSet.getInt(3),
                rowSet.getInt(4),
                rowSet.getInt(5)
        );
    }

    @Override
    public Rule update(Integer id, Rule entity) {
        Object[] params = new Object[] { entity.getAlphabetFromId(), entity.getAlphabetToId(), entity.getSymbolInId(), entity.getSymbolOutId(),  id };
        int[] types = new int[] { Types.INTEGER, Types.INTEGER, Types.INTEGER, Types.INTEGER, Types.INTEGER  };
        SqlRowSet rowSet = jdbcOperations.queryForRowSet(updateQuery, params, types);
        if (!rowSet.next()) {
            return null;
        }
        return new Rule(
                rowSet.getInt(1),
                rowSet.getInt(2),
                rowSet.getInt(3),
                rowSet.getInt(4),
                rowSet.getInt(5)
        );
    }

    @Override
    public Rule delete(Integer id) {
        Object[] params = new Object[] { id };
        int[] types = new int[] {Types.INTEGER };
        SqlRowSet rowSet = jdbcOperations.queryForRowSet(deleteQuery, params, types);
        if (!rowSet.next()) {
            return null;
        }
        return new Rule(
                rowSet.getInt(1),
                rowSet.getInt(2),
                rowSet.getInt(3),
                rowSet.getInt(4),
                rowSet.getInt(5)
        );
    }
}
