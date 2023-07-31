class PlaceHold {
  public boolean hasBranch(int lineNumber) {
    return branches.containsKey(new Integer(lineNumber));
  }
}
