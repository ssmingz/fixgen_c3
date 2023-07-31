class PlaceHold {
  public void update() {
    checkWidget();
    if (!isVisible()) {
      return;
    }
    Display display = getDisplay();
    display.update();
  }
}
