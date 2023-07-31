class PlaceHold {
  public Image getImage(int columnIndex) {
    checkWidget();
    int validColumnCount = Math.max(1, parent.columns.length);
    if (!((0 <= columnIndex) && (columnIndex < validColumnCount))) {
      return null;
    }
    if (columnIndex == 0) {
      return getImage();
    }
    return images[columnIndex];
  }
}
