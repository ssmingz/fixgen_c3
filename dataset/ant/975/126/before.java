class PlaceHold {
  protected void runStatements(Reader reader, PrintStream out)
      throws SQLException, IOException, TaskException {
    String sql = "";
    String line = "";
    BufferedReader in = new BufferedReader(reader);
    try {
      while ((line = in.readLine()) != null) {
        line = line.trim();
        final String value = line;
        line = "" + getContext().resolveValue(value);
        if (line.startsWith("//")) {
          continue;
        }
        if (line.startsWith("--")) {
          continue;
        }
        StringTokenizer st = new StringTokenizer(line);
        if (st.hasMoreTokens()) {
          String token = st.nextToken();
          if ("REM".equalsIgnoreCase(token)) {
            continue;
          }
        }
        sql += " " + line;
        sql = sql.trim();
        if (line.indexOf("--") >= 0) {
          sql += "\n";
        }
        if ((delimiterType.equals(DelimiterType.NORMAL) && sql.endsWith(delimiter))
            || (delimiterType.equals(DelimiterType.ROW) && line.equals(delimiter))) {
          getLogger().debug("SQL: " + sql);
          execSQL(sql.substring(0, sql.length() - delimiter.length()), out);
          sql = "";
        }
      }
      if (!sql.equals("")) {
        execSQL(sql, out);
      }
    } catch (SQLException e) {
      throw e;
    }
  }
}
