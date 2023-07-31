class PlaceHold {
  void destroyChild(TabItem item) {
    int index = indexOf(item);
    if (index == (-1)) {
      return;
    }
    if (items.length == 1) {
      items = null;
      selectedIndex = -1;
      topTabIndex = 0;
      if (!inDispose) {
        Control control = item.control;
        if ((control != null) && (!control.isDisposed())) {
          control.setVisible(false);
        }
        redraw();
      }
    } else {
      TabItem[] newItems = new TabItem[items.length - 1];
      System.arraycopy(items, 0, newItems, 0, index);
      System.arraycopy(items, index + 1, newItems, index, (items.length - index) - 1);
      items = newItems;
      if (selectedIndex == index) {
        if (!inDispose) {
          Control control = item.control;
          if ((control != null) && (!control.isDisposed())) {
            control.setVisible(false);
          }
          selectedIndex = -1;
          setSelection(Math.max(0, index - 1), true);
        }
      } else if (selectedIndex > index) {
        selectedIndex--;
      }
      if (topTabIndex == items.length) {
        --topTabIndex;
      }
    }
    if ((topTabIndex > 0) && (!isTabScrolling())) {
      topTabIndex = 0;
    }
    if (!inDispose) {
      layoutItems();
      redrawTabs();
    }
  }
}
