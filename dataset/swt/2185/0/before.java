class PlaceHold {
  void handleMouseUp(Event event) {
    mouseDown = false;
    mouseDoubleClick = false;
    event.y -= topMargin;
    endAutoScroll();
  }
}
