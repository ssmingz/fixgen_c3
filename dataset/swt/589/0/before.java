class PlaceHold {
  public void setBackground(Color color) {
    super.setBackground(color);
    bg = color;
    if (text != null) {
      text.setBackground(color);
    }
    if (popupCalendar != null) {
      popupCalendar.setBackground(color);
    }
  }
}
