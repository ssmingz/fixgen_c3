class PlaceHold {
  public void setHeaderVisible(boolean show) {
    checkWidget();
    headerVisibility = (show) ? OS.Visibility_Visible : OS.Visibility_Collapsed;
    updateHeaderVisibility();
    for (int i = 0; i < columnCount; i++) {
      TreeColumn column = getColumn(i);
      column.updateImage();
      column.updateText();
    }
  }
}
