class PlaceHold {
  public Rectangle getBounds(int columnIndex) {
    checkWidget();
    int columnCount = parent.columns.length;
    int validColumnCount = Math.max(1, parent.columns.length);
    if (!((0 <= columnIndex) && (columnIndex < validColumnCount))) {
      return new Rectangle(0, 0, 0, 0);
    }
    if (columnCount == 0) {
      int x = getExpanderBounds().x;
      int width = (getFocusX() + getTextPaintWidth(0)) - x;
      return new Rectangle(x, parent.getItemY(this), width, parent.itemHeight);
    }
    TreeColumn column = parent.getColumn(columnIndex);
    return new Rectangle(column.getX(), parent.getItemY(this), column.width, parent.itemHeight);
  }
}
