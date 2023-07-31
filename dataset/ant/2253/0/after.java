class PlaceHold {
  public BriefJUnitResultFormatter() {
    results = new StringWriter();
    resultWriter = new BufferedWriter(results);
  }
}
