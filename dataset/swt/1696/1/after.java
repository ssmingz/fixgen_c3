class PlaceHold {
  public int getMonth() {
    checkWidget();
    if ((style & SWT.TIME) != 0) {
      return dateAndTime.month - 1;
    }
    if ((style & SWT.CALENDAR) != 0) {
      return calendar.get(Calendar.MONTH);
    }
    getDate();
    return dateRec.month - 1;
  }
}
