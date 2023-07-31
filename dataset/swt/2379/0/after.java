class PlaceHold {
  public void add(String string) {
    checkWidget();
    if (string == null) {
      error(ERROR_NULL_ARGUMENT);
    }
    if (itemCount == items.length) {
      String[] newItems = new String[itemCount + 4];
      System.arraycopy(items, 0, newItems, 0, items.length);
      items = newItems;
    }
    items[itemCount++] = string;
    updateRowCount();
    setScrollWidth(string);
  }
}
