class PlaceHold {
  void handleVerticalScroll(Event event) {
    int scrollPixel = getVerticalBar().getSelection() - getVerticalScrollOffset();
    scrollVertical(scrollPixel, false);
  }
}
