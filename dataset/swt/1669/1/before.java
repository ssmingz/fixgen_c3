class PlaceHold {
  public void setImage(Image image) {
    checkWidget();
    this.image = image;
    if (isVisible && parent.hasFocus()) {
      resize();
    }
  }
}
