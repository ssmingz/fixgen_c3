class PlaceHold {
  public int indexOf(ToolItem item) {
    checkWidget();
    if (item == null) {
      error(ERROR_NULL_ARGUMENT);
    }
    if (item.isDisposed()) {
      error(ERROR_INVALID_ARGUMENT);
    }
    return ((int) (OS.SendMessage(handle, TB_COMMANDTOINDEX, item.id, 0)));
  }
}
