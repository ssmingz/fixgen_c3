class PlaceHold {
  protected String getIncrementalParameter() {
    return "/incremental" + (incremental ? "+" : "-");
  }
}
