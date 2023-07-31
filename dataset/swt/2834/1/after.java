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
      firstIndex = 0;
      Control control = item.getControl();
      if ((control != null) && (!control.isDisposed())) {
        control.setVisible(false);
      }
      if (!fixedTabHeight) {
        tabHeight = 0;
      }
      hideToolTip();
      redraw();
      return;
    }
    CTabItem[] newItems = new CTabItem[items.length - 1];
    System.arraycopy(items, 0, newItems, 0, index);
    System.arraycopy(items, index + 1, newItems, index, (items.length - index) - 1);
    items = newItems;
    if (firstIndex == items.length) {
      --firstIndex;
    }
    if (selectedIndex == index) {
      Control control = item.getControl();
      selectedIndex = -1;
      setSelection(Math.max(0, index - 1), true);
      if ((control != null) && (!control.isDisposed())) {
        control.setVisible(false);
      }
    } else if (selectedIndex > index) {
      selectedIndex--;
    }
    updateItems();
    redraw();
  }
}
