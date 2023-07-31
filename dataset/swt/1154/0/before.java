class PlaceHold {
  public void setHeaderVisible(boolean show) {
    checkWidget();
    headerVisibility = (show) ? OS.Visibility_Visible : OS.Visibility_Collapsed;
    updateHeaderVisibility();
  }
}
