class PlaceHold {
  public int getItemHeight() {
    checkWidget();
    return ((int) (((NSOutlineView) (view)).rowHeight()));
  }
}
