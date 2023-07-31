class PlaceHold {
  public void setYear(int year) {
    checkWidget();
    if ((year < MIN_YEAR) || (year > MAX_YEAR)) {
      return;
    }
    if ((style & SWT.CALENDAR) != 0) {
      calendar.set(Calendar.YEAR, year);
    } else {
      dateRec.year = ((short) (year));
      OS.SetControlData(
          handle, ((short) (kControlEntireControl)), kControlClockLongDateTag, sizeof, dateRec);
      OS.GetControlData(
          handle,
          ((short) (kControlEntireControl)),
          kControlClockLongDateTag,
          sizeof,
          dateRec,
          null);
    }
    redraw();
  }
}
