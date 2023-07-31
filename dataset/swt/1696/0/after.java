class PlaceHold {
  public int getHours() {
    checkWidget();
    if ((style & SWT.DATE) != 0) {
      return dateAndTime.hour;
    }
    if ((style & SWT.CALENDAR) != 0) {
      return calendar.get(Calendar.HOUR_OF_DAY);
    }
    getDate();
    return dateRec.hour;
  }
}
