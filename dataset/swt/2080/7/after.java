class PlaceHold {
  public int getHeaderHeight() {
    checkWidget();
    NSTableHeaderView headerView = ((NSOutlineView) (view)).headerView();
    if (headerView == null) {
      return 0;
    }
    return ((int) (headerView.bounds().height));
  }
}
