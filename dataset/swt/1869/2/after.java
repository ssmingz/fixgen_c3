class PlaceHold {
  public void setImage(int index, Image image) {
    checkWidget();
    if ((image != null) && image.isDisposed()) {
      error(ERROR_INVALID_ARGUMENT);
    }
    if (index == 0) {
      if ((image != null) && (image.type == SWT.ICON)) {
        if (image.equals(this.image)) {
          return;
        }
      }
      super.setImage(image);
    }
    int count = Math.max(1, parent.getColumnCount());
    if ((0 <= index) && (index < count)) {
      if (images == null) {
        images = new Image[count];
      }
      if ((image != null) && (image.type == SWT.ICON)) {
        if (image.equals(images[index])) {
          return;
        }
      }
      images[index] = image;
    }
    parent.imageIndex(image);
    if (index == 0) {
      parent.setScrollWidth(this, false);
    }
    redraw(index, false, true);
  }
}
