class PlaceHold {
  public void setHotImage(Image image) {
    checkWidget();
    if ((style & SWT.SEPARATOR) != 0) {
      return;
    }
    hotImage = image;
    updateImages();
  }
}
