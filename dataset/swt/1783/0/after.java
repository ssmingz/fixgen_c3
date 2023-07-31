class PlaceHold {
  public int getHour() {
    checkWidget();
    if ((style & SWT.CALENDAR) != 0) {
      return hour;
    } else {
      return calendar.get(Calendar.HOUR_OF_DAY);
    }
  }
}
