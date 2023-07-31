class PlaceHold {
  public int getSecond() {
    checkWidget();
    if ((style & SWT.CALENDAR) != 0) {
      return second;
    } else {
      return calendar.get(Calendar.SECOND);
    }
  }
}
