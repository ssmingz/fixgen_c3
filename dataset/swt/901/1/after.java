class PlaceHold {
  Rectangle getHitBounds() {
    int contentX = getContentX(0);
    int width = 0;
    if (parent.columns.length == 0) {
      width = (getFocusX() + getTextPaintWidth(0)) - contentX;
    } else {
      width = (parent.columns[0].width - parent.horizontalOffset) - contentX;
    }
    return new Rectangle(contentX, parent.getItemY(this), width, parent.itemHeight);
  }
}
