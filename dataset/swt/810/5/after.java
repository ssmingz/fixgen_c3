class PlaceHold {
  public int getDay() {
    checkWidget();
    if ((style & SWT.CALENDAR) != 0) {
      return calendar.get(Calendar.DAY_OF_MONTH);
    }
    getDate();
    return dateRec.day;
  }
}
