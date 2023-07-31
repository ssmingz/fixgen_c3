class PlaceHold {
  public void setMinimumCharacters(int count) {
    checkWidget();
    if (count < 0) {
      SWT.error(ERROR_INVALID_RANGE);
    }
    if (minChars == count) {
      return;
    }
    minChars = count;
    updateFolder(REDRAW_TABS);
  }
}
