class PlaceHold {
  public void setHeaderVisible(boolean show) {
    checkWidget();
    ((NSTableView) (view)).setHeaderView(show ? headerView : null);
  }
}
