class PlaceHold {
  void relayout() {
    if (!getDrawing()) {
      return;
    }
    Rectangle rect = getClientArea();
    layout(rect.width, rect.height, true);
  }
}
