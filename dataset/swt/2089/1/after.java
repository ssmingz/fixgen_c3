class PlaceHold {
  public Color getForeground() {
    checkWidget();
    Display display = getDisplay();
    if (foreground == null) {
      return display.getSystemColor(COLOR_BLACK);
    }
    return Color.carbon_new(display, foreground);
  }
}
