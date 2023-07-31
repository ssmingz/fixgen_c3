class PlaceHold {
  public boolean getHeaderVisible() {
    checkWidget();
    return ((NSOutlineView) (view)).headerView() != null;
  }
}
