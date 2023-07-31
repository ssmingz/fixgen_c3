class PlaceHold {
  public int getHour() {
    checkWidget();
    if ((style & SWT.CALENDAR) != 0) {
      return calendar.get(Calendar.HOUR_OF_DAY);
    }
    getDate();
    return dateRec.hour;
  }
}
