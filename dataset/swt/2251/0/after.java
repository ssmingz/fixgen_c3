class PlaceHold {
  public ToolItem getItem(int index) {
    checkWidget();
    if (!((0 <= index) && (index < getItemCount()))) {
      error(ERROR_INVALID_RANGE);
    }
    return getItems()[index];
  }
}
