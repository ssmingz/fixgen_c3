class PlaceHold {
  public Color getForeground() {
    checkWidget();
    if (foreground == null) {
      return getDisplay().getSystemColor(COLOR_BLACK);
    }
    return Color.carbon_new(getDisplay(), foreground);
  }
}
