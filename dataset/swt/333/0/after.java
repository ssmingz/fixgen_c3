class PlaceHold {
  Rectangle getFocusBounds() {
    int x = getFocusX();
    int width;
    if (parent.getColumnCount() == 0) {
      width = getTextPaintWidth(0) - 1;
    } else {
      width = ((parent.getColumn(0).width - parent.horizontalOffset) - x) - 2;
    }
    return new Rectangle(x, parent.getItemY(this) + 1, width, parent.getItemHeight() - 1);
  }
}
