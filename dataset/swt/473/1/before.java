class PlaceHold {
  public int indexOf(ToolItem item) {
    checkWidget();
    if (item == null) {
      error(ERROR_NULL_ARGUMENT);
    }
    return OS.SendMessage(handle, TB_COMMANDTOINDEX, item.id, 0);
  }
}
