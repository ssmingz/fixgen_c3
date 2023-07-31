class PlaceHold {
  void destroyItem(TableItem item) {
    int index = 0;
    while (index < itemCount) {
      if (items[index] == item) {
        break;
      }
      index++;
    }
    if (index != (itemCount - 1)) {
      fixSelection(index, false);
    }
    System.arraycopy(items, index + 1, items, index, (--itemCount) - index);
    items[itemCount] = null;
    updateRowCount();
    if (itemCount == 0) {
      setTableEmpty();
    }
  }
}
