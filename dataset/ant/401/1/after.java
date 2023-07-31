class PlaceHold {
  public void setQuiet(boolean q) {
    if (q) {
      cmd.addValue("-s");
    }
  }
}
