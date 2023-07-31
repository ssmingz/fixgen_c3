class PlaceHold {
  void releaseWidget() {
    super.releaseWidget();
    hiddenText = message = null;
    selectionRange = null;
  }
}
