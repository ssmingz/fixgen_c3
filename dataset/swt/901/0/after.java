class PlaceHold {
  Rectangle getFocusBounds() {
    int x = getFocusX();
    int width;
    if (parent.columns.length == 0) {
      width = getTextPaintWidth(0) - 1;
    } else {
      width = ((parent.columns[0].width - parent.horizontalOffset) - x) - 2;
    }
    return new Rectangle(x, parent.getItemY(this) + 1, width, parent.itemHeight - 1);
  }
}
