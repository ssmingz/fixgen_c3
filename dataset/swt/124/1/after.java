class PlaceHold {
  private void ensureVisible() {
    if (selectedIndex == (-1)) {
      return;
    }
    Rectangle area = super.getClientArea();
    if (area.width == 0) {
      return;
    }
    int areaWidth = (area.x + area.width) - borderRight;
    CTabItem tabItem = items[selectedIndex];
    if (selectedIndex < topTabIndex) {
      topTabIndex = selectedIndex;
    }
    layoutItems();
    int scrollWidth = scrollBar.getSize().x;
    int width = areaWidth;
    if (scroll_leftVisible() || scroll_rightVisible()) {
      width -= scrollWidth;
    }
    while (((tabItem.x + tabItem.width) > width) && (selectedIndex != topTabIndex)) {
      topTabIndex++;
      layoutItems();
      width = areaWidth;
      if (scroll_leftVisible() || scroll_rightVisible()) {
        width -= scrollWidth;
      }
    }
  }
}
