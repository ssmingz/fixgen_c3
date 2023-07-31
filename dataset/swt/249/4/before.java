class PlaceHold {
  public void setImage(Image image) {
    checkWidget();
    if ((image != null) && image.isDisposed()) {
      error(ERROR_INVALID_ARGUMENT);
    }
    super.setImage(image);
    if ((parent.sortColumn != this) || (parent.sortDirection != SWT.NULL)) {
      setImage(image, false, false);
    }
  }
}
