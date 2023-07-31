class PlaceHold {
  void releaseWidget() {
    super.releaseWidget();
    strings = null;
    images = null;
    cellFont = null;
    cellBackground = cellForeground = null;
  }
}
