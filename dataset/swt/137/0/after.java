class PlaceHold {
  public void setMoveable(boolean moveable) {
    checkWidget();
    if (moveable) {
      ((NSTableView) (parent.view)).setAllowsColumnReordering(true);
    }
  }
}
