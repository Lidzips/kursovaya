package com.example.demo.repository;

import com.example.demo.entity.Alphabet;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import java.sql.Types;
import java.util.ArrayList;

@Repository
public class AlphabetRepository implements IRestRepository<Alphabet> {
    protected final JdbcOperations jdbcOperations;

    private static String selectQuery = "SELECT \"id\", \"name\"" +
            "FROM \"alphabet\" " +
            "ORDER BY \"id\"";

    private static String selectByIdQuery = "SELECT  \"id\", \"name\"" +
            "FROM \"alphabet\" " +
            "WHERE \"id\" = ?";

    private static String selectIdByNameQuery = "SELECT  \"id\" " +
            "FROM \"alphabet\" " +
            "WHERE \"name\" = ?";

    private static String selectBySymbolIdQuery = "SELECT  \"a\".\"id\", \"a\".\"name\" " +
            "FROM \"alphabet\" AS \"a\" " +
            "JOIN \"symbol\" AS \"s\" " +
            "ON \"a\".\"id\" = \"s\".\"alphabet_id\" " +
            "WHERE \"s\".\"id\" = ?";

    private static String selectFromByRuleIdQuery = "SELECT  \"a\".\"id\", \"a\".\"name\" " +
            "FROM \"alphabet\" AS \"a\" " +
            "JOIN \"rule\" AS \"r\" " +
            "ON \"a\".\"id\" = \"r\".\"alphabet_from_id\" " +
            "WHERE \"r\".\"id\" = ?";

    private static String selectToByRuleIdQuery = "SELECT  \"a\".\"id\", \"a\".\"name\" " +
            "FROM \"alphabet\" AS \"a\" " +
            "JOIN \"rule\" AS \"r\" " +
            "ON \"a\".\"id\" = \"r\".\"alphabet_to_id\" " +
            "WHERE \"r\".\"id\" = ?";

    private static String insertQuery = "INSERT INTO \"alphabet\"(\"name\", \"symbol_id\")" +
            "VALUES (?, ?) " +
            "RETURNING \"id\", \"name\", \"symbol_id\"";

    private static String updateQuery = "UPDATE \"alphabet\" " +
            "SET \"name\" = ?, \"symbol_id\" = ? " +
            "WHERE \"id\" = ? " +
            "RETURNING \"id\", \"name\", \"symbol_id\"";

    private static String deleteQuery = "DELETE FROM \"alphabet\" " +
            "WHERE \"id\" = ? " +
            "RETURNING \"id\", \"name\", \"symbol_id\"";

    public AlphabetRepository(JdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
    }

    @Override
    public Alphabet[] select() {
        ArrayList<Alphabet> values = new ArrayList<Alphabet>();
        SqlRowSet rowSet = jdbcOperations.queryForRowSet(selectQuery);
        while (rowSet.next()) {
            values.add(new Alphabet(
                    rowSet.getInt(1),
                    rowSet.getString(2)
            ));
        }
        Alphabet[] result = new Alphabet[values.size()];
        result = values.toArray(result);
        return result;
    }

    @Override
    public Alphabet select(Integer id) {
        Object[] params = new Object[] { id };
        int[] types = new int[] { Types.INTEGER };
        SqlRowSet rowSet = jdbcOperations.queryForRowSet(selectByIdQuery, params, types);
        if (!rowSet.next()) {
            return null;
        }
        return new Alphabet(
                rowSet.getInt(1),
                rowSet.getString(2)
        );
    }

    public Alphabet[] selectBySymbolId(Integer symbolId) {
        ArrayList<Alphabet> values = new ArrayList<Alphabet>();
        Object[] params = new Object[] { symbolId };
        int[] types = new int[] { Types.INTEGER };
        SqlRowSet rowSet = jdbcOperations.queryForRowSet(selectBySymbolIdQuery, params, types);
        while (rowSet.next()) {
            values.add(new Alphabet(
                    rowSet.getInt(1),
                    rowSet.getString(2)
            ));
        }
        Alphabet[] result = new Alphabet[values.size()];
        result = values.toArray(result);
        return result;
    }

    public Integer selectIdByName(String name) {
        ArrayList<Alphabet> values = new ArrayList<Alphabet>();
        Object[] params = new Object[] { name };
        int[] types = new int[] { Types.VARCHAR };
        SqlRowSet rowSet = jdbcOperations.queryForRowSet(selectIdByNameQuery, params, types);
        if (!rowSet.next()) {
            return null;
        }
        return rowSet.getInt(1);
    }

    public Alphabet selectFromByRuleId(Integer ruleId) {
        Object[] params = new Object[] { ruleId };
        int[] types = new int[] { Types.INTEGER };
        SqlRowSet rowSet = jdbcOperations.queryForRowSet(selectFromByRuleIdQuery, params, types);
        if (!rowSet.next()) {
            return null;
        }
        return new Alphabet(
                rowSet.getInt(1),
                rowSet.getString(2)
        );
    }

    public Alphabet selectToByRuleId(Integer ruleId) {
        Object[] params = new Object[] { ruleId };
        int[] types = new int[] { Types.INTEGER };
        SqlRowSet rowSet = jdbcOperations.queryForRowSet(selectToByRuleIdQuery, params, types);
        if (!rowSet.next()) {
            return null;
        }
        return new Alphabet(
                rowSet.getInt(1),
                rowSet.getString(2)
        );
    }

    @Override
    public Alphabet insert(Alphabet entity) {
        Object[] params = new Object[] { entity.getName() };
        int[] types = new int[] { Types.VARCHAR };
        SqlRowSet rowSet = jdbcOperations.queryForRowSet(insertQuery, params, types);
        if (!rowSet.next()) {
            return null;
        }
        return new Alphabet(
                rowSet.getInt(1),
                rowSet.getString(2)
        );
    }

    @Override
    public Alphabet update(Integer id, Alphabet entity) {
        Object[] params = new Object[] { entity.getName(), id };
        int[] types = new int[] { Types.VARCHAR, Types.INTEGER };
        SqlRowSet rowSet = jdbcOperations.queryForRowSet(updateQuery, params, types);
        if (!rowSet.next()) {
            return null;
        }
        return new Alphabet(
                rowSet.getInt(1),
                rowSet.getString(2)
        );
    }

    @Override
    public Alphabet delete(Integer id) {
        Object[] params = new Object[] { id };
        int[] types = new int[] {Types.INTEGER };
        SqlRowSet rowSet = jdbcOperations.queryForRowSet(deleteQuery, params, types);
        if (!rowSet.next()) {
            return null;
        }
        return new Alphabet(
                rowSet.getInt(1),
                rowSet.getString(2)
        );
    }
}
