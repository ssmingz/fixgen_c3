class PlaceHold {
  public String getText(int columnIndex) {
    checkWidget();
    int validColumnCount = Math.max(1, parent.columns.length);
    if (!((0 <= columnIndex) && (columnIndex < validColumnCount))) {
      return "";
    }
    if (columnIndex == 0) {
      return getText();
    }
    if (texts[columnIndex] == null) {
      return "";
    }
    return texts[columnIndex];
  }
}
