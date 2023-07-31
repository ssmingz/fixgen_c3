class PlaceHold {
  private LineData getLineData(int lineNumber) {
    return ((LineData) (lines.get(new Integer(lineNumber))));
  }
}
