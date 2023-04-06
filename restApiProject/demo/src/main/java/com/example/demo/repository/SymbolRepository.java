package com.example.demo.repository;

import com.example.demo.entity.Symbol;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import java.sql.Types;
import java.util.ArrayList;

@Repository
public class SymbolRepository implements IRestRepository<Symbol> {
    protected final JdbcOperations jdbcOperations;

    private static String selectQuery = "SELECT \"id\", \"character\", \"alphabet_id\" " +
            "FROM \"symbol\" " +
            "ORDER BY \"id\"";

    private static String selectByIdQuery = "SELECT \"id\", \"character\", \"alphabet_id\" " +
            "FROM \"symbol\" " +
            "WHERE \"id\" = ?";

    private static String selectByAlphabetQuery = "SELECT \"id\", \"character\", \"alphabet_id\" " +
            "FROM \"symbol\" " +
            "WHERE \"alphabet_id\" = ?";

    private static String selectByRuleQuery = "SELECT s2.character " +
            "FROM symbol s1 " +
            "INNER JOIN rule r ON s1.id = r.symbol_in_id " +
            "INNER JOIN alphabet a1 ON r.alphabet_from_id = a1.id " +
            "INNER JOIN alphabet a2 ON r.alphabet_to_id = a2.id " +
            "INNER JOIN symbol s2 ON r.symbol_out_id = s2.id " +
            "WHERE s1.character = ? AND a1.name = ? AND a2.name = ?";

    private static String insertQuery = "INSERT INTO \"symbol\"" +
            "VALUES (?, ?) " +
            "RETURNING \"id\", \"character\", \"alphabet_id\"";

    private static String updateQuery = "UPDATE \"symbol\" " +
            "SET \"character\" = ?, \"alphabet_id\" = ?" +
            "WHERE \"id\" = ? " +
            "RETURNING \"id\", \"character\", \"alphabet_id\"";

    private static String deleteQuery = "DELETE FROM \"symbol\" " +
            "WHERE \"id\" = ? " +
            "RETURNING \"id\", \"character\", \"alphabet_id\"";

    public SymbolRepository(JdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
    }

    @Override
    public Symbol[] select() {
        ArrayList<Symbol> values = new ArrayList<Symbol>();
        SqlRowSet rowSet = jdbcOperations.queryForRowSet(selectQuery);
        while (rowSet.next()) {
            values.add(new Symbol(
                    rowSet.getInt(1),
                    rowSet.getString(2),
                    rowSet.getInt(3)
            ));
        }
        Symbol[] result = new Symbol[values.size()];
        result = values.toArray(result);
        return result;
    }

    @Override
    public Symbol select(Integer id) {
        Object[] params = new Object[] { id };
        int[] types = new int[] {Types.INTEGER };
        SqlRowSet rowSet = jdbcOperations.queryForRowSet(selectByIdQuery, params, types);
        if (!rowSet.next()) {
            return null;
        }
        return new Symbol(
                rowSet.getInt(1),
                rowSet.getString(2),
                rowSet.getInt(3)
        );
    }

    public Symbol[] selectByAlphabet(Integer alphabetId) {
        ArrayList<Symbol> values = new ArrayList<Symbol>();
        Object[] params = new Object[] { alphabetId };
        int[] types = new int[] { Types.INTEGER };
        SqlRowSet rowSet = jdbcOperations.queryForRowSet(selectByAlphabetQuery, params, types);
        while (rowSet.next()) {
            values.add(new Symbol(
                    rowSet.getInt(1),
                    rowSet.getString(2),
                    rowSet.getInt(3)
            ));
        }
        Symbol[] result = new Symbol[values.size()];
        result = values.toArray(result);
        return result;
    }

    public String selectByRule(String character, String alphabet1, String alphabet2) {
        ArrayList<Symbol> values = new ArrayList<Symbol>();
        Object[] params = new Object[] { character, alphabet1, alphabet2 };
        int[] types = new int[] { Types.VARCHAR, Types.VARCHAR, Types.VARCHAR };
        SqlRowSet rowSet = jdbcOperations.queryForRowSet(selectByRuleQuery, params, types);
        if (!rowSet.next()) {
            return null;
        }
        return rowSet.getString(1);
    }

    @Override
    public Symbol insert(Symbol entity) {
        Object[] params = new Object[] { entity.getCharacter(), entity.getAlphabetId() };
        int[] types = new int[] { Types.VARCHAR, Types.INTEGER};
        SqlRowSet rowSet = jdbcOperations.queryForRowSet(insertQuery, params, types);
        if (!rowSet.next()) {
            return null;
        }
        return new Symbol(
                rowSet.getInt(1),
                rowSet.getString(2),
                rowSet.getInt(3)
        );
    }

    @Override
    public Symbol update(Integer id, Symbol entity) {
        Object[] params = new Object[] { entity.getCharacter(), entity.getAlphabetId(),  id };
        int[] types = new int[] { Types.VARCHAR, Types.INTEGER, Types.INTEGER };
        SqlRowSet rowSet = jdbcOperations.queryForRowSet(updateQuery, params, types);
        if (!rowSet.next()) {
            return null;
        }
        return new Symbol (
                rowSet.getInt(1),
                rowSet.getString(2),
                rowSet.getInt(3)
        );
    }

    @Override
    public Symbol delete(Integer id) {
        Object[] params = new Object[] { id };
        int[] types = new int[] {Types.INTEGER };
        SqlRowSet rowSet = jdbcOperations.queryForRowSet(deleteQuery, params, types);
        if (!rowSet.next()) {
            return null;
        }
        return new Symbol(
                rowSet.getInt(1),
                rowSet.getString(2),
                rowSet.getInt(3)
        );
    }
}
