class PlaceHold {
  public void setHeaderVisible(boolean show) {
    checkWidget();
    ((NSOutlineView) (view)).setHeaderView(show ? headerView : null);
  }
}
