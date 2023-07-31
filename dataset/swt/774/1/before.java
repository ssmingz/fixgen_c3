class PlaceHold {
  public ToolItem getItem(int index) {
    checkWidget();
    ToolItem[] items = getItems();
    if ((0 <= index) && (index < items.length)) {
      return items[index];
    }
    error(ERROR_INVALID_RANGE);
    return null;
  }
}
