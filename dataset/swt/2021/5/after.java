class PlaceHold {
  public void setHeight(int height) {
    if (height < 0) {
      SWT.error(ERROR_INVALID_ARGUMENT);
    }
    this.height = height;
    this.string = null;
  }
}
