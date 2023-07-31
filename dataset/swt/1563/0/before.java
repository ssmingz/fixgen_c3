class PlaceHold {
  void dropDown(boolean drop) {
    if (drop == isDropped()) {
      return;
    }
    if (!drop) {
      popup.setVisible(false);
      text.setFocus();
      return;
    }
    if (getShell() != popup.getParent()) {
      String[] items = list.getItems();
      int selectionIndex = list.getSelectionIndex();
      list.removeListener(Dispose, listener);
      popup.dispose();
      popup = null;
      list = null;
      createPopup(items, selectionIndex);
    }
    Point size = getSize();
    int itemCount = list.getItemCount();
    itemCount = (itemCount == 0) ? visibleItemCount : Math.min(visibleItemCount, itemCount);
    int itemHeight = list.getItemHeight() * itemCount;
    Point listSize = list.computeSize(DEFAULT, itemHeight);
    list.setBounds(1, 1, Math.max(size.x - 2, listSize.x), listSize.y);
    int index = list.getSelectionIndex();
    if (index != (-1)) {
      list.setTopIndex(index);
    }
    Display display = getDisplay();
    Rectangle listRect = list.getBounds();
    Rectangle parentRect = display.map(getParent(), null, getBounds());
    Point comboSize = getSize();
    Rectangle displayRect = getMonitor().getClientArea();
    int width = Math.max(comboSize.x, listRect.width + 2);
    int height = listRect.height + 2;
    int x = parentRect.x;
    int y = parentRect.y + comboSize.y;
    if ((y + height) > (displayRect.y + displayRect.height)) {
      y = parentRect.y - height;
    }
    popup.setBounds(x, y, width, height);
    popup.setVisible(true);
    list.setFocus();
  }
}
