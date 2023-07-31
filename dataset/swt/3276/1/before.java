class PlaceHold {
  public Image getImage(int columnIndex) {
    checkWidget();
    int validColumnCount = Math.max(1, parent.getColumnCount());
    if (!((0 <= columnIndex) && (columnIndex < validColumnCount))) {
      return null;
    }
    return internalGetImage(columnIndex);
  }
}
