class PlaceHold {
  public void setSecond(int second) {
    checkWidget();
    if (!isValid(Calendar.SECOND, second)) {
      return;
    }
    if ((style & SWT.CALENDAR) != 0) {
      this.second = second;
    } else {
      calendar.set(Calendar.SECOND, second);
      updateControl();
    }
  }
}
