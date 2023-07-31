class PlaceHold {
  Rectangle getHitBounds() {
    int contentX = getContentX(0);
    int width = 0;
    if (parent.getColumnCount() == 0) {
      width = (getFocusX() + getTextPaintWidth(0)) - contentX;
    } else {
      width = (parent.getColumn(0).getWidth() - parent.horizontalOffset) - contentX;
    }
    return new Rectangle(contentX, parent.getItemY(this), width, parent.getItemHeight());
  }
}
