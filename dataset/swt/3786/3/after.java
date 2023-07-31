class PlaceHold {
  public void setFont(Font font) {
    checkWidget();
    if ((font != null) && font.isDisposed()) {
      error(ERROR_INVALID_ARGUMENT);
    }
  }
}
