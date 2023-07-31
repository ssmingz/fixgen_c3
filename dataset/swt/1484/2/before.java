class PlaceHold {
  void doScrollVertical(Event event) {
    int newSelection = getVerticalBar().getSelection();
    Rectangle clientArea = getClientArea();
    GC gc = new GC(this);
    gc.copyArea(
        0, 0, clientArea.width, clientArea.height, 0, (topIndex - newSelection) * itemHeight);
    gc.dispose();
    topIndex = newSelection;
  }
}
