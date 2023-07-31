class PlaceHold {
  void destroyItem(CTabItem item) {
    if (inDispose) {
      return;
    }
    int index = indexOf(item);
    if (index == (-1)) {
      return;
    }
    if (items.length == 1) {
      items = new CTabItem[0];
      priority = new int[0];
      firstIndex = -1;
      selectedIndex = -1;
      Control control = item.getControl();
      if ((control != null) && (!control.isDisposed())) {
        control.setVisible(false);
      }
      setToolTipText(null);
      GC gc = new GC(this);
      setButtonBounds(gc);
      gc.dispose();
      redraw();
      return;
    }
    CTabItem[] newItems = new CTabItem[items.length - 1];
    System.arraycopy(items, 0, newItems, 0, index);
    System.arraycopy(items, index + 1, newItems, index, (items.length - index) - 1);
    items = newItems;
    int[] newPriority = new int[priority.length - 1];
    int next = 0;
    for (int i = 0; i < priority.length; i++) {
      if (priority[i] == index) {
        continue;
      }
      newPriority[next++] = (priority[i] > index) ? priority[i] - 1 : priority[i];
    }
    priority = newPriority;
    if (selectedIndex == index) {
      Control control = item.getControl();
      selectedIndex = -1;
      int nextSelection = (mru) ? priority[0] : Math.max(0, index - 1);
      setSelection(nextSelection, true);
      if ((control != null) && (!control.isDisposed())) {
        control.setVisible(false);
      }
    } else if (selectedIndex > index) {
      selectedIndex--;
    }
    updateItems();
    redrawTabs();
  }
}
