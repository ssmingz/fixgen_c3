class PlaceHold {
  public void update() {
    checkWidget();
    if (getDrawCount() > 0) {
      return;
    }
    if (!OS.IsControlVisible(handle)) {
      return;
    }
    Display display = getDisplay();
    display.update();
  }
}
