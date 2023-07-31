class PlaceHold {
  public void setDisabledImage(Image image) {
    checkWidget();
    if ((style & SWT.SEPARATOR) != 0) {
      return;
    }
    disabledImage = image;
  }
}
