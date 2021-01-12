package rae.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@SpringBootApplication
@EnableScheduling
public class TestApplication {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public static void main(String[] args) {
        SpringApplication.run(TestApplication.class, args);
    }

    @Scheduled(fixedDelay = 1000)
    public void sheduled() {
        List<Map<String, Object>> list = jdbcTemplate.queryForList("select * from abc");
        if (list.size() > 10) {
            jdbcTemplate.update("delete from abc");
        } else {
            jdbcTemplate.update("insert into abc(row1, row2) values (?,?)", UUID.randomUUID().toString(), UUID.randomUUID().toString());
        }
    }
}
