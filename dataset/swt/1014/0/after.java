class PlaceHold {
  public void setMinutes(int minutes) {
    checkWidget();
    SYSTEMTIME systime = new SYSTEMTIME();
    int msg = ((style & SWT.CALENDAR) != 0) ? OS.MCM_GETCURSEL : OS.DTM_GETSYSTEMTIME;
    OS.SendMessage(handle, msg, 0, systime);
    msg = ((style & SWT.CALENDAR) != 0) ? OS.MCM_SETCURSEL : OS.DTM_SETSYSTEMTIME;
    systime.wMinute = ((short) (minutes));
    OS.SendMessage(handle, msg, 0, systime);
    if ((((style & SWT.CALENDAR) != 0) && (minutes >= 0)) && (minutes <= 59)) {
      time.wMinute = ((short) (minutes));
    }
  }
}
