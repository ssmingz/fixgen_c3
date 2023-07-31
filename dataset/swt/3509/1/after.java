class PlaceHold {
  public Color getBackground() {
    checkWidget();
    if (background == null) {
      return getDisplay().getSystemColor(COLOR_WHITE);
    }
    return Color.carbon_new(getDisplay(), background);
  }
}
