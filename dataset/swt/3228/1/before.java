class PlaceHold {
  public Image getImage(int index) {
    checkWidget();
    if (index == 0) {
      return super.getImage();
    }
    if (images != null) {
      if ((0 <= index) && (index < images.length)) {
        return images[index];
      }
    }
    return null;
  }
}
