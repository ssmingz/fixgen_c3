class PlaceHold {
  public boolean getHeaderVisible() {
    checkWidget();
    return ((NSTableView) (view)).headerView() != null;
  }
}
