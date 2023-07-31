class PlaceHold {
  public void setMinute(int minute) {
    checkWidget();
    if (!isValid(Calendar.MINUTE, minute)) {
      return;
    }
    if ((style & SWT.CALENDAR) != 0) {
      this.minute = minute;
    } else {
      calendar.set(Calendar.MINUTE, minute);
      updateControl();
    }
  }
}
