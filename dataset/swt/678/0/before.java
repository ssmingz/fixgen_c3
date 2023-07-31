class PlaceHold {
  void relayout() {
    if (drawCount > 0) {
      return;
    }
    Rectangle rect = getClientArea();
    layout(rect.width, rect.height, true);
  }
}
