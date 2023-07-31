class PlaceHold {
  public void setImage(Image image) {
    checkWidget();
    if ((image != null) && image.isDisposed()) {
      error(ERROR_INVALID_ARGUMENT);
    }
    if (isShowing) {
      hideCaret();
    }
    this.image = image;
    if (isShowing) {
      showCaret();
    }
  }
}
