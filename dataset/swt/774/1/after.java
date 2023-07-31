class PlaceHold {
  public ToolItem getItem(int index) {
    checkWidget();
    if ((0 <= index) && (index < itemCount)) {
      return items[index];
    }
    error(ERROR_INVALID_RANGE);
    return null;
  }
}
