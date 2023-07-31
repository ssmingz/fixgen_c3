class PlaceHold {
  public int getItemHeight() {
    checkWidget();
    int result = ((int) (OS.SendMessage(handle, CB_GETITEMHEIGHT, 0, 0)));
    if (result == OS.CB_ERR) {
      error(ERROR_CANNOT_GET_ITEM_HEIGHT);
    }
    return result;
  }
}
