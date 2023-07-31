class PlaceHold {
  public void setImage(Image image) {
    checkWidget();
    if ((style & SWT.SEPARATOR) != 0) {
      return;
    }
    this.image = image;
    isImage = true;
    redraw();
  }
}
