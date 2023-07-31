class PlaceHold {
  protected void runStatements(Reader reader, PrintStream out) throws SQLException, IOException {
    StringBuffer sql = new StringBuffer();
    String line = "";
    BufferedReader in = new BufferedReader(reader);
    while ((line = in.readLine()) != null) {
      if (!keepformat) {
        line = line.trim();
      }
      line = getProject().replaceProperties(line);
      if (!keepformat) {
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
      }
      if (!keepformat) {
        sql.append(" " + line);
      } else {
        sql.append("\n" + line);
      }
      if (!keepformat) {
        if (line.indexOf("--") >= 0) {
          sql.append("\n");
        }
      }
      if ((delimiterType.equals(DelimiterType.NORMAL) && sql.toString().endsWith(delimiter))
          || (delimiterType.equals(DelimiterType.ROW) && line.equals(delimiter))) {
        execSQL(sql.substring(0, sql.length() - delimiter.length()), out);
        sql.replace(0, sql.length(), "");
      }
    }
    if (!sql.equals("")) {
      execSQL(sql.toString(), out);
    }
  }
}
