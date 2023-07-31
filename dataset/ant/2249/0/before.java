class PlaceHold {
  private String readLines(InputStream is) throws IOException {
    InputStreamReader isr = new InputStreamReader(is, "ISO-8859-1");
    BufferedReader br = new BufferedReader(isr);
    if (logicalLines.size() > 0) {
      logicalLines.add(new Blank());
    }
    String s = br.readLine();
    boolean continuation = false;
    boolean comment = false;
    StringBuffer fileBuffer = new StringBuffer();
    StringBuffer logicalLineBuffer = new StringBuffer();
    while (s != null) {
      fileBuffer.append(s).append(LS);
      if (continuation) {
        s = "\n" + s;
      } else {
        comment = s.matches("^( |\t|\f)*(#|!).*");
      }
      if (!comment) {
        continuation = requiresContinuation(s);
      }
      logicalLineBuffer.append(s);
      if (!continuation) {
        LogicalLine line = null;
        if (comment) {
          line = new Comment(logicalLineBuffer.toString());
        } else if (logicalLineBuffer.toString().trim().length() == 0) {
          line = new Blank();
        } else {
          line = new Pair(logicalLineBuffer.toString());
          String key = unescape(((Pair) (line)).getName());
          if (keyedPairLines.containsKey(key)) {
            remove(key);
          }
          keyedPairLines.put(key, new Integer(logicalLines.size()));
        }
        logicalLines.add(line);
        logicalLineBuffer.setLength(0);
      }
      s = br.readLine();
    }
    return fileBuffer.toString();
  }
}
