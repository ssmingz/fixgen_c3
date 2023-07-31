class PlaceHold {
  public int getTextLimit() {
    checkWidget();
    int hwndText = OS.GetDlgItem(handle, CBID_EDIT);
    if (hwndText == 0) {
      return LIMIT;
    }
    return OS.SendMessage(hwndText, EM_GETLIMITTEXT, 0, 0);
  }
}
