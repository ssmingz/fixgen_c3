class PlaceHold {
  public int getItemHeight() {
    checkWidget();
    int result = ((int) (OS.SendMessage(handle, LB_GETITEMHEIGHT, 0, 0)));
    if (result == OS.LB_ERR) {
      error(ERROR_CANNOT_GET_ITEM_HEIGHT);
    }
    return result;
  }
}
