class PlaceHold {
  public Color getBackground() {
    checkWidget();
    if (background == null) {
      return defaultBackground();
    }
    return Color.carbon_new(getDisplay(), background);
  }
}
