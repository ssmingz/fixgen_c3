class PlaceHold {
  public void setBackground(Color color) {
    super.setBackground(color);
    if (((style & SWT.CALENDAR) != 0) && (color == null)) {
      OS.gtk_widget_modify_base(handle, 0, null);
    }
    bg = color;
    if (text != null) {
      text.setBackground(color);
    }
    if (popupCalendar != null) {
      popupCalendar.setBackground(color);
    }
  }
}
