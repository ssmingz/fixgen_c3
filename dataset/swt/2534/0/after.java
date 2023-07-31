class PlaceHold {
  public boolean getLinesVisible() {
    checkWidget();
    return ((NSOutlineView) (view)).usesAlternatingRowBackgroundColors();
  }
}
