class PlaceHold {
  public Rectangle getImageBounds(int columnIndex) {
    checkWidget();
    if (!parent.checkData(this, true)) {
      error(ERROR_WIDGET_DISPOSED);
    }
    int validColumnCount = Math.max(1, parent.columns.length);
    if (!((0 <= columnIndex) && (columnIndex < validColumnCount))) {
      return new Rectangle(0, 0, 0, 0);
    }
    int padding = parent.getCellPadding();
    int startX = getContentX(columnIndex);
    int itemHeight = parent.itemHeight;
    int imageSpaceY = itemHeight - (2 * padding);
    int y = parent.getItemY(this);
    Image image = getImage(columnIndex, false);
    if (image == null) {
      return new Rectangle(startX, y + padding, 0, imageSpaceY);
    }
    Rectangle imageBounds = image.getBounds();
    int drawWidth;
    if (columnIndex == 0) {
      int imageSpaceX = parent.col0ImageWidth;
      drawWidth = Math.min(imageSpaceX, imageBounds.width);
    } else {
      drawWidth = imageBounds.width;
    }
    int drawHeight = Math.min(imageSpaceY, imageBounds.height);
    return new Rectangle(startX, y + ((itemHeight - drawHeight) / 2), drawWidth, drawHeight);
  }
}
