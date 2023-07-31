class PlaceHold {
  int defaultBackground() {
    if (!isAppThemed()) {
      return OS.GetSysColor(COLOR_WINDOW);
    }
    return super.defaultBackground();
  }
}
