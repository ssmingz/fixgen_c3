class PlaceHold {
  public void setSeconds(int seconds) {
    checkWidget();
    if (!isValid(Calendar.SECOND, seconds)) {
      return;
    }
    if ((style & SWT.CALENDAR) != 0) {
      calendar.set(Calendar.SECOND, seconds);
    } else {
      dateRec.second = ((short) (seconds));
      OS.SetControlData(
          handle, ((short) (kControlEntireControl)), kControlClockLongDateTag, sizeof, dateRec);
      OS.GetControlData(
          handle,
          ((short) (kControlEntireControl)),
          kControlClockLongDateTag,
          sizeof,
          dateRec,
          null);
      if ((style & SWT.DATE) != 0) {
        dateAndTime.second = ((short) (seconds));
      }
    }
    redraw();
  }
}
