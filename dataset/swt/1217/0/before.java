class PlaceHold {
  public void setImage(int columnIndex, Image value) {
    checkWidget();
    if ((value != null) && value.isDisposed()) {
      error(ERROR_INVALID_ARGUMENT);
    }
    TableColumn[] columns = parent.columns;
    int validColumnCount = Math.max(1, columns.length);
    if (!((0 <= columnIndex) && (columnIndex < validColumnCount))) {
      return;
    }
    Image image = getImage(columnIndex);
    if (value == image) {
      return;
    }
    if ((value != null) && value.equals(image)) {
      return;
    }
    if (columnIndex == 0) {
      super.setImage(value);
    } else {
      images[columnIndex] = value;
    }
    if (columns.length > 0) {
      GC gc = new GC(parent);
      gc.setFont(getFont(columnIndex));
      computeDisplayText(columnIndex, gc);
      textWidths[columnIndex] = gc.textExtent(getDisplayText(columnIndex)).x;
      gc.dispose();
    }
    if (value == null) {
      redrawItem();
      return;
    }
    if (parent.imageHeight == 0) {
      int oldItemHeight = parent.itemHeight;
      parent.setImageHeight(value.getBounds().height);
      if (oldItemHeight != parent.itemHeight) {
        if (columnIndex == 0) {
          parent.col0ImageWidth = value.getBounds().width;
          if (columns.length > 0) {
            GC gc = new GC(parent);
            TableItem[] rootItems = parent.items;
            for (int i = 0; i < parent.itemsCount; i++) {
              rootItems[i].updateColumnWidth(columns[0], gc);
            }
            gc.dispose();
          }
        }
        parent.redraw();
        return;
      }
    }
    if ((columnIndex == 0) && (parent.col0ImageWidth == 0)) {
      parent.col0ImageWidth = value.getBounds().width;
      if (columns.length == 0) {
        parent.redraw();
      } else {
        GC gc = new GC(parent);
        TableItem[] rootItems = parent.items;
        for (int i = 0; i < parent.itemsCount; i++) {
          rootItems[i].updateColumnWidth(columns[0], gc);
        }
        gc.dispose();
        parent.redraw(columns[0].getX(), 0, columns[0].width, parent.getClientArea().height, true);
      }
      return;
    }
    redrawItem();
  }
}
