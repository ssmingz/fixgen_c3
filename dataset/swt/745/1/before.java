class PlaceHold {
  public void update() {
    checkWidget();
    if (!OS.IsControlVisible(handle)) {
      return;
    }
    Display display = getDisplay();
    display.update();
  }
}
