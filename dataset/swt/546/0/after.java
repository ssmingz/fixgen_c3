class PlaceHold {
  public void setImage(Image image) {
    checkWidget();
    if ((image != null) && image.isDisposed()) {
      error(ERROR_INVALID_ARGUMENT);
    }
    if ((style & SWT.ARROW) != 0) {
      return;
    }
    this.image = image;
    if ((style & (SWT.RADIO | SWT.CHECK)) == 0) {
      ((NSButton) (view)).setImage(image != null ? image.handle : null);
      view.setNeedsDisplay(true);
    }
    updateAlignment();
  }
}
