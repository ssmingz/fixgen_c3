class PlaceHold {
  void paint(GC gc, int yPosition) {
    if (isVisible() == false) {
      return;
    }
    Tree parent = getParent();
    Font font = getFont();
    gc.setFont(font);
    Point paintPosition = new Point(getPaintStartX(), yPosition);
    Point extent = getSelectionExtent();
    gc.setForeground(parent.CONNECTOR_LINE_COLOR);
    paintPosition = drawVerticalItemConnector(gc, paintPosition);
    paintPosition = drawHierarchyIndicator(gc, paintPosition);
    paintPosition = drawHorizontalItemConnector(gc, paintPosition);
    gc.setForeground(parent.getForeground());
    if (isCheckable() == true) {
      paintPosition = drawCheckbox(gc, new Point(paintPosition.x, yPosition));
    }
    paintPosition = drawImage(gc, new Point(paintPosition.x, yPosition));
    if (isSelected() == true) {
      gc.setBackground(getSelectionBackgroundColor());
      gc.setForeground(getSelectionForegroundColor());
      gc.fillRectangle(paintPosition.x, paintPosition.y, extent.x, extent.y);
    } else {
      gc.setBackground(getBackground());
      gc.setForeground(getForeground());
      if (getBackground() != parent.getBackground()) {
        gc.fillRectangle(paintPosition.x, paintPosition.y, extent.x, extent.y);
      }
    }
    if (text != null) {
      gc.drawString(text, getTextXPos(), paintPosition.y + getTextYPosition(gc), true);
    }
    if (this == parent.getInsertItem()) {
      drawInsertMark(gc, paintPosition);
    }
    drawSelectionFocus(gc, paintPosition);
  }
}
