class PlaceHold {
  void handleMouseMove(Event event) {
    if (!mouseDown) {
      return;
    }
    if ((event.stateMask & SWT.BUTTON1) == 0) {
      return;
    }
    event.y -= topMargin;
    doMouseLocationChange(event.x, event.y, true);
    update();
    doAutoScroll(event);
  }
}
