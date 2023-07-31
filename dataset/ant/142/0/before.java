class BriefJUnitResultFormatter {
  public BriefJUnitResultFormatter() {
    results = new StringWriter();
    resultWriter = new PrintWriter(results);
  }
}
