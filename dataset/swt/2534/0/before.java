class PlaceHold {
  public boolean getLinesVisible() {
    checkWidget();
    return ((NSTableView) (view)).usesAlternatingRowBackgroundColors();
  }
}
