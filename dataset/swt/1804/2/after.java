class PlaceHold {
  public void setFont(Font font) {
    checkWidget();
    super.setFont(font);
    if (text != null) {
      text.setFont(font);
    }
    redraw();
  }
}
