class PlaceHold {
  void createExampleWidgets() {
    int style = getDefaultStyle();
    if (dateButton.getSelection()) {
      style |= SWT.DATE;
    }
    if (timeButton.getSelection()) {
      style |= SWT.TIME;
    }
    if (calendarButton.getSelection()) {
      style |= SWT.CALENDAR;
    }
    if (shortButton.getSelection()) {
      style |= SWT.SHORT;
    }
    if (mediumButton.getSelection()) {
      style |= SWT.MEDIUM;
    }
    if (longButton.getSelection()) {
      style |= SWT.LONG;
    }
    if (dropDownButton.getSelection()) {
      style |= SWT.DROP_DOWN;
    }
    if (borderButton.getSelection()) {
      style |= SWT.BORDER;
    }
    dateTime1 = new DateTime(dateTimeGroup, style);
  }
}
