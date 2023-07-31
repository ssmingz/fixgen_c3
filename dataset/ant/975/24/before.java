class PlaceHold {
  protected void printResults(PrintStream out) throws SQLException {
    ResultSet rs = null;
    do {
      rs = statement.getResultSet();
      if (rs != null) {
        getLogger().debug("Processing new result set.");
        ResultSetMetaData md = rs.getMetaData();
        int columnCount = md.getColumnCount();
        StringBuffer line = new StringBuffer();
        if (showheaders) {
          for (int col = 1; col < columnCount; col++) {
            line.append(md.getColumnName(col));
            line.append(",");
          }
          line.append(md.getColumnName(columnCount));
          out.println(line);
          line.setLength(0);
        }
        while (rs.next()) {
          boolean first = true;
          for (int col = 1; col <= columnCount; col++) {
            String columnValue = rs.getString(col);
            if (columnValue != null) {
              columnValue = columnValue.trim();
            }
            if (first) {
              first = false;
            } else {
              line.append(",");
            }
            line.append(columnValue);
          }
          out.println(line);
          line.setLength(0);
        }
      }
    } while (statement.getMoreResults());
    out.println();
  }
}
