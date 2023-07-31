class PlaceHold {
  protected void execSQL(String sql, PrintStream out) throws SQLException {
    if ("".equals(sql.trim())) {
      return;
    }
    ResultSet resultSet = null;
    try {
      totalSql++;
      log("SQL: " + sql, MSG_VERBOSE);
      boolean ret;
      int updateCount = 0;
      int updateCountTotal = 0;
      ret = statement.execute(sql);
      updateCount = statement.getUpdateCount();
      resultSet = statement.getResultSet();
      do {
        if (!ret) {
          if (updateCount != (-1)) {
            updateCountTotal += updateCount;
          }
        } else if (print) {
          printResults(resultSet, out);
        }
        ret = statement.getMoreResults();
        if (ret) {
          updateCount = statement.getUpdateCount();
          resultSet = statement.getResultSet();
        }
      } while (ret);
      log(updateCountTotal + " rows affected", MSG_VERBOSE);
      if (print && showtrailers) {
        out.println(updateCountTotal + " rows affected");
      }
      SQLWarning warning = conn.getWarnings();
      while (warning != null) {
        log(warning + " sql warning", MSG_VERBOSE);
        warning = warning.getNextWarning();
      }
      conn.clearWarnings();
      goodSql++;
    } catch (SQLException e) {
      log("Failed to execute: " + sql, MSG_ERR);
      if (!onError.equals("continue")) {
        throw e;
      }
      log(e.toString(), MSG_ERR);
    } finally {
      if (resultSet != null) {
        resultSet.close();
      }
    }
  }
}
