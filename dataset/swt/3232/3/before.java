class PlaceHold {
  public void add(String string, int index) {
    checkWidget();
    if (string == null) {
      error(ERROR_NULL_ARGUMENT);
    }
    if (!((0 <= index) && (index <= getItemCount()))) {
      error(ERROR_ITEM_NOT_ADDED);
    }
    String[] items = getItems();
    String[] newItems = new String[items.length + 1];
    System.arraycopy(items, 0, newItems, 0, items.length);
    newItems[index] = string;
    setItems(newItems);
  }
}
