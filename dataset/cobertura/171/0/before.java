class PlaceHold {
  public boolean isValidSourceLineNumber(int lineNumber) {
    return lines.containsKey(new Integer(lineNumber));
  }
}
