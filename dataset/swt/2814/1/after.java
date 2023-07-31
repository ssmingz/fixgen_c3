class PlaceHold {
  void createItem(CTabItem2 item, int index) {
    if ((0 > index) || (index > getItemCount())) {
      SWT.error(ERROR_INVALID_RANGE);
    }
    CTabItem2[] newItems = new CTabItem2[items.length + 1];
    System.arraycopy(items, 0, newItems, 0, index);
    newItems[index] = item;
    System.arraycopy(items, index, newItems, index + 1, items.length - index);
    items = newItems;
    item.parent = this;
    if (selectedIndex >= index) {
      selectedIndex++;
    }
    if (items.length == 1) {
      topTabIndex = 0;
      if (!updateTabHeight(tabHeight)) {
        updateItems();
      }
      redraw();
    } else {
      updateItems();
      if ((index >= topTabIndex)
          && ((item.x + item.width)
              <= (((getSize().x - borderRight) - closeRect.width) - chevronRect.width))) {
        redraw();
      }
    }
  }
}
