class PlainJUnitResultFormatter {
  public PlainJUnitResultFormatter() {
    inner = new StringWriter();
    wri = new BufferedWriter(inner);
  }
}
