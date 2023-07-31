class PlaceHold {
  public void setLinesVisible(boolean show) {
    checkWidget();
    ((NSTableView) (view)).setUsesAlternatingRowBackgroundColors(show);
  }
}
