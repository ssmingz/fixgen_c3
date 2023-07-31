class PlaceHold {
  void destroyItem(CTabItem item) {
    if (inDispose) {
      return;
    }
    int index = indexOf(item);
    if (index == (-1)) {
      return;
    }
    insertionIndex = -2;
    if (items.length == 1) {
      items = new CTabItem[0];
      selectedIndex = -1;
      topTabIndex = 0;
      Control control = item.getControl();
      if ((control != null) && (!control.isDisposed())) {
        control.setVisible(false);
      }
      closeBar.setVisible(false);
      redraw();
      return;
    }
    CTabItem[] newItems = new CTabItem[items.length - 1];
    System.arraycopy(items, 0, newItems, 0, index);
    System.arraycopy(items, index + 1, newItems, index, (items.length - index) - 1);
    items = newItems;
    if (topTabIndex == items.length) {
      --topTabIndex;
    }
    if (selectedIndex == index) {
      Control control = item.getControl();
      if ((control != null) && (!control.isDisposed())) {
        control.setVisible(false);
      }
      selectedIndex = -1;
      setSelectionNotify(Math.max(0, index - 1));
    } else if (selectedIndex > index) {
      selectedIndex--;
    }
    layoutItems();
    ensureVisible();
    redrawTabArea(-1);
  }
}
