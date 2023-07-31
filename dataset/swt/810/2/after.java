class PlaceHold {
  public int getMonth() {
    checkWidget();
    if ((style & SWT.CALENDAR) != 0) {
      return calendar.get(Calendar.MONTH) + 1;
    }
    getDate();
    return dateRec.month;
  }
}
