class PlaceHold {
  protected String getIncrementalParameter() {
    return "/incremental" + (_incremental ? "+" : "-");
  }
}
