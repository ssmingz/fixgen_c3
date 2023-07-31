class PlaceHold {
  public void setTabHeight(int height) {
    if (height < 0) {
      SWT.error(ERROR_INVALID_ARGUMENT);
    }
    if (fixedTabHeight == height) {
      return;
    }
    fixedTabHeight = height;
    notifyListeners(Resize, new Event());
  }
}
