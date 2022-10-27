package com.codecool.dungeoncrawl.dao;

import com.codecool.dungeoncrawl.model.GameState;
import com.codecool.dungeoncrawl.model.PlayerModel;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GameStateDaoJdbc implements GameStateDao {

    private final DataSource dataSource;

    public GameStateDaoJdbc(DataSource dataSource) {
        this.dataSource = dataSource;
    }
    @Override
    public void add(GameState state) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "INSERT INTO game_state (current_map, saved_at, player_id) VALUES (?, ?, ?)";
            PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, state.getCurrentMap());
            statement.setDate(2, new Date(System.currentTimeMillis()));
            statement.setInt(3, state.getPlayer().getId());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            resultSet.next();
            state.setId(resultSet.getInt(1));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(GameState state) {
        try (Connection conn = dataSource.getConnection()) {
            String query = "SELECT player_id FROM game_state WHERE saved_at = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, state.getSavedAt());
            ResultSet rs = ps.executeQuery();
            rs.next();
            int playerId = rs.getInt(1);
            String query2 = "UPDATE game_state SET current_map = ?, saved_at = ? WHERE saved_at = ?";
            PreparedStatement ps2 = conn.prepareStatement(query2);
            ps2.setString(1, state.getCurrentMap());
            ps2.setDate(2, new Date(System.currentTimeMillis()));
            ps2.setString(3, state.getSavedAt());
            ps2.executeUpdate();
            String query3 = "UPDATE player SET hp = ?, x = ?, y = ? WHERE id = ?";
            PreparedStatement ps3 = conn.prepareStatement(query3);
            ps3.setInt(1, state.getPlayer().getHp());
            ps3.setInt(2, state.getPlayer().getX());
            ps3.setInt(3, state.getPlayer().getY());
            ps3.setInt(4, playerId);
            ps3.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public GameState get(int id) {
        return null;
    }

    @Override
    public List<GameState> getAll() {
        return null;
    }
}
