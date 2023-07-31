class PlaceHold {
  public int indexOf(CoolItem item) {
    checkWidget();
    if (item == null) {
      error(ERROR_NULL_ARGUMENT);
    }
    if (item.isDisposed()) {
      error(ERROR_INVALID_ARGUMENT);
    }
    return OS.SendMessage(handle, RB_IDTOINDEX, item.id, 0);
  }
}
