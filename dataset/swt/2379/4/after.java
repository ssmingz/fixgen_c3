class PlaceHold {
  public void add(String string, int index) {
    checkWidget();
    if (string == null) {
      error(ERROR_NULL_ARGUMENT);
    }
    if (!((0 <= index) && (index <= itemCount))) {
      error(ERROR_INVALID_RANGE);
    }
    if (itemCount == items.length) {
      String[] newItems = new String[itemCount + 4];
      System.arraycopy(items, 0, newItems, 0, items.length);
      items = newItems;
    }
    System.arraycopy(items, index, items, index + 1, (itemCount++) - index);
    items[index] = string;
    updateRowCount();
    if (index != itemCount) {
      fixSelection(index, true);
    }
    setScrollWidth(string);
  }
}
