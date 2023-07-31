class PlaceHold {
  public void setMoveable(boolean moveable) {
    checkWidget();
    if (moveable) {
      ((NSOutlineView) (parent.view)).setAllowsColumnReordering(true);
    }
  }
}
