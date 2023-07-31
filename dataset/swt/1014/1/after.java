class PlaceHold {
  public void setSeconds(int seconds) {
    checkWidget();
    SYSTEMTIME systime = new SYSTEMTIME();
    int msg = ((style & SWT.CALENDAR) != 0) ? OS.MCM_GETCURSEL : OS.DTM_GETSYSTEMTIME;
    OS.SendMessage(handle, msg, 0, systime);
    msg = ((style & SWT.CALENDAR) != 0) ? OS.MCM_SETCURSEL : OS.DTM_SETSYSTEMTIME;
    systime.wSecond = ((short) (seconds));
    OS.SendMessage(handle, msg, 0, systime);
    if ((((style & SWT.CALENDAR) != 0) && (seconds >= 0)) && (seconds <= 59)) {
      time.wSecond = ((short) (seconds));
    }
  }
}
