class PlaceHold {
  public MenuItem getItem(int index) {
    checkWidget();
    int[] commandID = new int[1];
    if (OS.GetMenuItemCommandID(handle, ((short) (index + 1)), commandID) != OS.kNoErr) {
      error(ERROR_INVALID_RANGE);
    }
    return parent.findMenuItem(commandID[0]);
  }
}
