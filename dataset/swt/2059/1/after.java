class PlaceHold {
  Rectangle getHitBounds() {
    int contentX = getContentX(0);
    int width = 0;
    TreeColumn[] columns = parent.columns;
    if (columns.length == 0) {
      int textPaintWidth = textWidths[0] + (2 * MARGIN_TEXT);
      width = (getFocusX() + textPaintWidth) - contentX;
    } else {
      width = (columns[0].width - contentX) - parent.horizontalOffset;
    }
    return new Rectangle(contentX, parent.getItemY(this), width, parent.itemHeight);
  }
}
