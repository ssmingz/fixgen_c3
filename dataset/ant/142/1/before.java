class PlainJUnitResultFormatter {
  public PlainJUnitResultFormatter() {
    inner = new StringWriter();
    wri = new PrintWriter(inner);
  }
}
