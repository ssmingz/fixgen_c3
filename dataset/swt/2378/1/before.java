class PlaceHold {
  public void setBackground(Color color) {
    if (color == null) {
      SWT.error(ERROR_NULL_ARGUMENT);
    }
    if (color.isDisposed()) {
      SWT.error(ERROR_INVALID_ARGUMENT);
    }
  }
}
