class PlaceHold {
  public Color getForeground() {
    checkWidget();
    if (foreground == null) {
      return defaultForeground();
    }
    return Color.carbon_new(getDisplay(), foreground);
  }
}
