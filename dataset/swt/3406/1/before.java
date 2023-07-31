class PlaceHold {
  public int getTextLimit() {
    checkWidget();
    long hwndText = OS.GetDlgItem(handle, CBID_EDIT);
    if (hwndText == 0) {
      return LIMIT;
    }
    return ((int) (OS.SendMessage(hwndText, EM_GETLIMITTEXT, 0, 0))) & 0x7fffffff;
  }
}
