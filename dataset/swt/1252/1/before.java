class PlaceHold {
  void releaseWidget() {
    super.releaseWidget();
    strings = null;
    images = null;
    cellBackground = cellForeground = cellFont = null;
  }
}
