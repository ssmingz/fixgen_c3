class PlaceHold {
  private boolean scroll_rightVisible() {
    if (topTabIndex < (items.length - 1)) {
      CTabItem tabItem = items[items.length - 1];
      int tabStopX = tabItem.x + tabItem.width;
      Rectangle area = super.getClientArea();
      if (tabStopX > ((area.x + area.width) - borderRight)) {
        return true;
      }
    }
    return false;
  }
}
