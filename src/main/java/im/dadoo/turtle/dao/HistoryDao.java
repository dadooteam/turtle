package im.dadoo.turtle.dao;

import im.dadoo.turtle.dao.mapper.HistoryMapper;
import im.dadoo.turtle.po.HistoryPo;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

@Repository
public class HistoryDao {

  private static final String INSERT_SQL =
      "INSERT INTO t_history(code,date,open,close,high,low,atr,n,status) " +
          "VALUES(:code,:date,:open,:close,:high,:low,:atr,:n,:status)";

  private static final String UPDATE_BY_CODE_AND_DATE_SQL =
      "UPDATE t_history SET open=:open,close=:close,high=:high,low=:low,atr=:atr,n=:n,status=:status WHERE code=:code AND date=:date";

  private static final String FIND_BY_CODE_AND_DATE_SQL =
      "SELECT * FROM t_history WHERE code=:code AND date=:date LIMIT 1";

  private static final String LIST_BY_CODE_SQL =
      "SELECT * FROM t_history  WHERE code=:code ORDER BY date DESC LIMIT :limit";

  @Resource
  private NamedParameterJdbcTemplate jdbcTemplate;

  @Resource
  private HistoryMapper historyMapper;

  public void insert(HistoryPo po) {
    MapSqlParameterSource sps = new MapSqlParameterSource();
    sps.addValue("code", po.getCode());
    sps.addValue("date", po.getDate());
    sps.addValue("open", po.getOpen());
    sps.addValue("close", po.getClose());
    sps.addValue("high", po.getHigh());
    sps.addValue("low", po.getLow());
    sps.addValue("atr", po.getAtr());
    sps.addValue("n", po.getN());
    sps.addValue("status", po.getStatus());
    this.jdbcTemplate.update(INSERT_SQL, sps);
  }

  public void updateByCodeAndDate(HistoryPo po) {
    MapSqlParameterSource sps = new MapSqlParameterSource();
    sps.addValue("code", po.getCode());
    sps.addValue("date", po.getDate());
    sps.addValue("open", po.getOpen());
    sps.addValue("close", po.getClose());
    sps.addValue("high", po.getHigh());
    sps.addValue("low", po.getLow());
    sps.addValue("atr", po.getAtr());
    sps.addValue("n", po.getN());
    sps.addValue("status", po.getStatus());
    this.jdbcTemplate.update(UPDATE_BY_CODE_AND_DATE_SQL, sps);
  }

  public HistoryPo findByCodeAndDate(String code, String date) {
    HistoryPo r = null;
    MapSqlParameterSource sps = new MapSqlParameterSource();
    sps.addValue("code", code);
    sps.addValue("date", date);
    try {
      r = this.jdbcTemplate.queryForObject(FIND_BY_CODE_AND_DATE_SQL, sps, this.historyMapper);
    } catch (Exception e) {}
    return r;
  }

  public List<HistoryPo> listByCode(String code, long limit) {
    MapSqlParameterSource sps = new MapSqlParameterSource();
    sps.addValue("code", code);
    sps.addValue("limit", limit);
    return this.jdbcTemplate.query(LIST_BY_CODE_SQL, sps, this.historyMapper);
  }
}
