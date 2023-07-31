class PlaceHold {
  public int getItemCount() {
    checkWidget();
    int count = ((int) (OS.SendMessage(handle, CB_GETCOUNT, 0, 0)));
    if (count == OS.CB_ERR) {
      error(ERROR_CANNOT_GET_COUNT);
    }
    return count;
  }
}
