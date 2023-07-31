class PlaceHold {
  void redraw() {
    if ((style & SWT.BAR) != 0) {
      display.addBar(this);
    } else {
      update();
    }
  }
}
