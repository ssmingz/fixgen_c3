class PlaceHold {
  public int indexOf(CoolItem item) {
    checkWidget();
    if (item == null) {
      error(ERROR_NULL_ARGUMENT);
    }
    return OS.SendMessage(handle, RB_IDTOINDEX, item.id, 0);
  }
}
