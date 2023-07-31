class PlaceHold {
  void paint(GC gc, TreeColumn column, boolean paintCellContent) {
    int columnIndex = 0;
    int x = 0;
    if (column != null) {
      columnIndex = column.getIndex();
      x = column.getX();
    }
    Rectangle clientArea = parent.getClientArea();
    if ((clientArea.x + clientArea.width) < x) {
      return;
    }
    Rectangle cellBounds = getBounds(columnIndex);
    int cellRightX = 0;
    if (column != null) {
      cellRightX = column.getX() + column.width;
    } else {
      cellRightX = cellBounds.x + cellBounds.width;
    }
    if (cellRightX < 0) {
      return;
    }
    gc.setClipping(x, cellBounds.y, cellRightX - x, cellBounds.height);
    int y = parent.getItemY(this);
    int padding = parent.getCellPadding();
    int itemHeight = parent.getItemHeight();
    Color background = getBackground(columnIndex);
    if (!background.equals(parent.getBackground())) {
      Color oldBackground = gc.getBackground();
      gc.setBackground(background);
      if (columnIndex == 0) {
        int focusX = getFocusX();
        gc.fillRectangle(focusX, y + 1, (cellRightX - focusX) - 1, itemHeight - 1);
      } else {
        gc.fillRectangle(
            cellBounds.x, cellBounds.y + 1, cellBounds.width - 1, cellBounds.height - 1);
      }
      gc.setBackground(oldBackground);
    }
    if (isSelected() && (columnIndex == 0)) {
      Color oldBackground = gc.getBackground();
      gc.setBackground(parent.selectionBackgroundColor);
      int startX = getFocusX() + 1;
      gc.fillRectangle(
          startX,
          cellBounds.y + padding,
          Math.max(0, (cellRightX - startX) - 2),
          Math.max(0, (cellBounds.height - (padding * 2)) + 1));
      gc.setBackground(oldBackground);
    }
    if (!paintCellContent) {
      return;
    }
    if (columnIndex == 0) {
      Rectangle expanderBounds = getExpanderBounds();
      Color oldForeground = gc.getForeground();
      gc.setForeground(parent.connectorLineColor);
      int lineX = expanderBounds.x + (expanderBounds.width / 2);
      int y2 = expanderBounds.y;
      if (items.length == 0) {
        y2 += expanderBounds.height / 2;
      }
      if ((parentItem != null) || (getIndex() != 0)) {
        gc.drawLine(lineX, y, lineX, y2);
      }
      if (!isLastChild()) {
        if (items.length != 0) {
          y2 += expanderBounds.height;
        }
        gc.drawLine(lineX, y2, lineX, y + itemHeight);
      }
      Point[] endpoints = getHconnectorEndpoints();
      gc.drawLine(
          endpoints[0].x, endpoints[0].y, endpoints[1].x - Tree.MARGIN_IMAGE, endpoints[1].y);
      TreeItem item = getParentItem();
      while (item != null) {
        if (!item.isLastChild()) {
          Rectangle itemExpanderBounds = item.getExpanderBounds();
          lineX = itemExpanderBounds.x + (itemExpanderBounds.width / 2);
          gc.drawLine(lineX, y, lineX, y + itemHeight);
        }
        item = item.getParentItem();
      }
      gc.setForeground(oldForeground);
      if (items.length > 0) {
        Image image = (expanded) ? parent.expandedImage : parent.collapsedImage;
        gc.drawImage(image, expanderBounds.x, expanderBounds.y);
      }
      if ((parent.style & SWT.CHECK) != 0) {
        Image baseImage = (grayed) ? parent.grayUncheckedImage : parent.uncheckedImage;
        Rectangle checkboxBounds = getCheckboxBounds();
        gc.drawImage(baseImage, checkboxBounds.x, checkboxBounds.y);
        if (checked) {
          Rectangle checkmarkBounds = parent.checkmarkImage.getBounds();
          int xInset = (checkboxBounds.width - checkmarkBounds.width) / 2;
          int yInset = (checkboxBounds.height - checkmarkBounds.height) / 2;
          gc.drawImage(parent.checkmarkImage, checkboxBounds.x + xInset, checkboxBounds.y + yInset);
        }
      }
    }
    Image image = images[columnIndex];
    String text = getText(columnIndex);
    Rectangle imageArea = getImageBounds(columnIndex);
    int startX = imageArea.x;
    gc.setClipping(
        startX,
        cellBounds.y + padding,
        (cellRightX - startX) - padding,
        cellBounds.height - (2 * padding));
    if (image != null) {
      Rectangle imageBounds = image.getBounds();
      gc.drawImage(
          image,
          0,
          0,
          imageBounds.width,
          imageBounds.height,
          imageArea.x,
          imageArea.y,
          imageArea.width,
          imageArea.height);
    }
    if (text.length() > 0) {
      boolean fontChanged = false;
      boolean foregroundChanged = false;
      Font oldFont = gc.getFont();
      Font font = getFont(columnIndex);
      if (!font.equals(oldFont)) {
        gc.setFont(font);
        fontChanged = true;
      }
      int fontHeight = getFontHeight(columnIndex);
      Color oldForeground = gc.getForeground();
      if (isSelected() && (columnIndex == 0)) {
        gc.setForeground(parent.selectionForegroundColor);
        foregroundChanged = true;
      } else {
        Color foreground = getForeground(columnIndex);
        if (!foreground.equals(oldForeground)) {
          gc.setForeground(foreground);
          foregroundChanged = true;
        }
      }
      gc.drawString(text, getTextX(columnIndex), y + ((itemHeight - fontHeight) / 2), true);
      if (foregroundChanged) {
        gc.setForeground(oldForeground);
      }
      if (fontChanged) {
        gc.setFont(oldFont);
      }
    }
  }
}
