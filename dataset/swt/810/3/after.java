class PlaceHold {
  public int getMinute() {
    checkWidget();
    if ((style & SWT.CALENDAR) != 0) {
      return calendar.get(Calendar.MINUTE);
    }
    getDate();
    return dateRec.minute;
  }
}
