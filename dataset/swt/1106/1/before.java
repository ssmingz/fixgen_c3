class PlaceHold {
  public int getTopIndex() {
    checkWidget();
    int bits = OS.GetWindowLong(handle, GWL_STYLE);
    if ((bits & OS.ES_MULTILINE) == 0) {
      return 0;
    }
    return OS.SendMessage(handle, EM_GETFIRSTVISIBLELINE, 0, 0);
  }
}
