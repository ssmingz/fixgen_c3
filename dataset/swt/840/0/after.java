class PlaceHold {
  public void setHour(int hour) {
    checkWidget();
    if (!isValid(Calendar.HOUR_OF_DAY, hour)) {
      return;
    }
    if ((style & SWT.CALENDAR) != 0) {
      this.hour = hour;
    } else {
      calendar.set(Calendar.HOUR_OF_DAY, hour);
      updateControl();
    }
  }
}
