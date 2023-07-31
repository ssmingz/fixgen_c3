class PlaceHold {
  public int getItemCount() {
    checkWidget();
    int result = OS.SendMessage(handle, LB_GETCOUNT, 0, 0);
    if (result == OS.LB_ERR) {
      error(ERROR_CANNOT_GET_COUNT);
    }
    return result;
  }
}
