class PlaceHold {
  public void store(OutputStream out, String header) throws IOException {
    OutputStreamWriter osw = new OutputStreamWriter(out, "ISO-8859-1");
    int skipLines = 0;
    int totalLines = logicalLines.size();
    if (header != null) {
      osw.write(("#" + header) + LS);
      if (((totalLines > 0) && (logicalLines.get(0) instanceof Comment))
          && header.equals(logicalLines.get(0).toString().substring(1))) {
        skipLines = 1;
      }
    }
    if ((totalLines > skipLines) && (logicalLines.get(skipLines) instanceof Comment)) {
      try {
        DateUtils.parseDateFromHeader(logicalLines.get(skipLines).toString().substring(1));
        skipLines++;
      } catch (ParseException pe) {
      }
    }
    osw.write(("#" + DateUtils.getDateForHeader()) + LS);
    boolean writtenSep = false;
    for (Iterator i = logicalLines.subList(skipLines, totalLines).iterator(); i.hasNext(); ) {
      LogicalLine line = ((LogicalLine) (i.next()));
      if (line instanceof Pair) {
        if (((Pair) (line)).isNew()) {
          if (!writtenSep) {
            osw.write(LS);
            writtenSep = true;
          }
        }
        osw.write(line.toString() + LS);
      } else if (line != null) {
        osw.write(line.toString() + LS);
      }
    }
    osw.close();
  }
}
