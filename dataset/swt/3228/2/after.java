class PlaceHold {
  public Image getImage(int index) {
    checkWidget();
    if (!parent.checkData(this, true)) {
      error(ERROR_WIDGET_DISPOSED);
    }
    if (index == 0) {
      return getImage();
    }
    if (images != null) {
      if ((0 <= index) && (index < images.length)) {
        return images[index];
      }
    }
    return null;
  }
}
