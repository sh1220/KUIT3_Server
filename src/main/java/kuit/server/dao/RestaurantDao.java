package kuit.server.dao;

import kuit.server.dto.restaurant.PostRestaurantRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.Map;
import java.util.Objects;

@Slf4j
@Repository
public class RestaurantDao {
    private final NamedParameterJdbcTemplate jdbcTemplate;
    public RestaurantDao(DataSource dataSource) {
        this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }


    public long createRestaurant(PostRestaurantRequest postRestaurantRequest) {
        String sql = "insert into store_delivery(category, store_name, delivery_area)" +
                "values(:category, :store_name, :delivery_area)";
        SqlParameterSource param = new BeanPropertySqlParameterSource(postRestaurantRequest);
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(sql, param, keyHolder);

        return Objects.requireNonNull(keyHolder.getKey()).longValue();


    }

    public boolean hasDuplicateStoreName(String storeName) {
        String sql = "select exists(select store_name from store_delivery where store_name=:store_name and status in ('active', 'dormant'))";
        Map<String, Object> param = Map.of("store_name", storeName);
        return Boolean.TRUE.equals(jdbcTemplate.queryForObject(sql, param, boolean.class));
    }
}
