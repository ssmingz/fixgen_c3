class PlaceHold {
  public int getSeconds() {
    checkWidget();
    if ((style & SWT.DATE) != 0) {
      return dateAndTime.second;
    }
    if ((style & SWT.CALENDAR) != 0) {
      return calendar.get(Calendar.SECOND);
    }
    getDate();
    return dateRec.second;
  }
}
