class PlaceHold {
  void handleVerticalScroll(Event event) {
    int scrollPixel = getVerticalBar().getSelection() - verticalScrollOffset;
    scrollVertical(scrollPixel, false);
  }
}
