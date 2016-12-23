package im.dadoo.turtle.dao.mapper;

import im.dadoo.turtle.po.HistoryPo;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class HistoryMapper implements RowMapper<HistoryPo> {

  @Override
  public HistoryPo mapRow(ResultSet rs, int i) throws SQLException {
    HistoryPo r = new HistoryPo();
    r.setCode(rs.getString("code"));
    r.setDate(rs.getString("date"));
    r.setOpen(rs.getDouble("open"));
    r.setClose(rs.getDouble("close"));
    r.setHigh(rs.getDouble("high"));
    r.setLow(rs.getDouble("low"));
    r.setAtr(rs.getDouble("atr"));
    r.setN(rs.getDouble("n"));
    r.setStatus(rs.getInt("status"));
    return r;
  }
}
