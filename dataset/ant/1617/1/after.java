class PlaceHold {
  protected void execSQL(String sql, PrintStream out) throws SQLException {
    if ("".equals(sql.trim())) {
      return;
    }
    try {
      totalSql++;
      log("SQL: " + sql, MSG_VERBOSE);
      if (!statement.execute(sql)) {
        log(statement.getUpdateCount() + " rows affected", MSG_VERBOSE);
      } else if (print) {
        printResults(out);
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
    }
  }
}
