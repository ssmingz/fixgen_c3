class PlaceHold {
  public int getMinutes() {
    checkWidget();
    SYSTEMTIME systime = new SYSTEMTIME();
    int msg = ((style & SWT.CALENDAR) != 0) ? OS.MCM_GETCURSEL : OS.DTM_GETSYSTEMTIME;
    OS.SendMessage(handle, msg, 0, systime);
    return systime.wMinute;
  }
}
