class PlaceHold {
  public boolean isSelected(int index) {
    checkWidget();
    int result = ((int) (OS.SendMessage(handle, LB_GETSEL, index, 0)));
    return (result != 0) && (result != OS.LB_ERR);
  }
}
