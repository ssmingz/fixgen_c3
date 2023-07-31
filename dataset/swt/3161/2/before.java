class PlaceHold {
  public int getSeconds() {
    checkWidget();
    if ((style & SWT.CALENDAR) != 0) {
      return calendar.get(Calendar.SECOND);
    }
    getDate();
    return dateRec.second;
  }
}
