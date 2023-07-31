class PlaceHold {
  public Color getBackground() {
    checkWidget();
    Display display = getDisplay();
    if (background == null) {
      return display.getSystemColor(COLOR_WHITE);
    }
    return Color.carbon_new(display, background);
  }
}
