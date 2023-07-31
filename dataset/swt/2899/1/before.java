class PlaceHold {
  public void setDay(int day) {
    checkWidget();
    if (!isValid(Calendar.DAY_OF_MONTH, day)) {
      return;
    }
    if ((style & SWT.CALENDAR) != 0) {
      setDay(day, false);
    } else {
      dateRec.day = ((short) (day));
      OS.SetControlData(
          handle, ((short) (kControlEntireControl)), kControlClockLongDateTag, sizeof, dateRec);
      OS.GetControlData(
          handle,
          ((short) (kControlEntireControl)),
          kControlClockLongDateTag,
          sizeof,
          dateRec,
          null);
      redraw();
    }
  }
}
