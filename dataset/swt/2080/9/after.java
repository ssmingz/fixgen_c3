class PlaceHold {
  public void setLinesVisible(boolean show) {
    checkWidget();
    ((NSOutlineView) (view)).setUsesAlternatingRowBackgroundColors(show);
  }
}
