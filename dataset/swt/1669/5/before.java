class PlaceHold {
  public void setImage(Image image) {
    checkWidget();
    if (isShowing) {
      hideCaret();
    }
    this.image = image;
    if (isShowing) {
      showCaret();
    }
  }
}
