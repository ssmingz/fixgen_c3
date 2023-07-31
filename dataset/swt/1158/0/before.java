class PlaceHold {
  int defaultBackground() {
    if ((OS.COMCTL32_MAJOR < 6) || (!OS.IsAppThemed())) {
      return OS.GetSysColor(COLOR_WINDOW);
    }
    return super.defaultBackground();
  }
}
