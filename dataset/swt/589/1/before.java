class PlaceHold {
  public void setForeground(Color color) {
    super.setForeground(color);
    fg = color;
    if (text != null) {
      text.setForeground(color);
    }
    if (popupCalendar != null) {
      popupCalendar.setForeground(color);
    }
  }
}
