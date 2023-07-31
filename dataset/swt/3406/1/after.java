class PlaceHold {
  public int getTextLimit() {
    checkWidget();
    long hwndText = OS.GetDlgItem(handle, CBID_EDIT);
    if (hwndText == 0) {
      return LIMIT;
    }
    int limit = ((int) (OS.SendMessage(hwndText, EM_GETLIMITTEXT, 0, 0))) & 0x7fffffff;
    if ((segments != null) && (limit < LIMIT)) {
      limit = Math.max(1, limit - segments.length);
    }
    return limit;
  }
}
