class PlaceHold {
  void redraw() {
    if (!isVisible()) {
      return;
    }
    if ((style & SWT.BAR) != 0) {
      display.addBar(this);
    } else {
      update();
    }
  }
}
