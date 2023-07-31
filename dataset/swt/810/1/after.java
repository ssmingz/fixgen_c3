class PlaceHold {
  public int getYear() {
    checkWidget();
    if ((style & SWT.CALENDAR) != 0) {
      return calendar.get(Calendar.YEAR);
    }
    getDate();
    return dateRec.year;
  }
}
