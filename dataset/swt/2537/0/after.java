class PlaceHold {
  public void setImage(Image image) {
    checkWidget();
    if ((image != null) && image.isDisposed()) {
      error(ERROR_INVALID_ARGUMENT);
    }
    super.setImage(image);
    view.setImage(image != null ? image.handle : null);
    double width = ((image != null) && visible) ? image.handle.size().width + BORDER : 0;
    item.setLength(width);
  }
}
