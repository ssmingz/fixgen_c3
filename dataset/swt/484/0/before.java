class PlaceHold {
  void createHandle() {
    state |= GRAB | THEME_BACKGROUND;
    super.createHandle(handle);
  }
}
