class PlaceHold {
  public int getMinutes() {
    checkWidget();
    if ((style & SWT.DATE) != 0) {
      return dateAndTime.minute;
    }
    if ((style & SWT.CALENDAR) != 0) {
      return calendar.get(Calendar.MINUTE);
    }
    getDate();
    return dateRec.minute;
  }
}
