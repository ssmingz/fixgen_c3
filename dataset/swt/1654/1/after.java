class PlaceHold {
  void releaseWidget() {
    Display display = getDisplay();
    display.removeMouseHoverTimeout(handle);
    super.releaseWidget();
    toolTipText = null;
    parent = null;
    menu = null;
    layoutData = null;
  }
}
