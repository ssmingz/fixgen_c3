class PlaceHold {
  public void setFont(Font font) {
    super.setFont(font);
    this.font = font;
    if (text != null) {
      text.setFont(font);
    }
    if (popupCalendar != null) {
      popupCalendar.setFont(font);
    }
    redraw();
  }
}
