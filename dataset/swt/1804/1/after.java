class PlaceHold {
  public void setBackground(Color color) {
    checkWidget();
    super.setBackground(color);
    if (text != null) {
      text.setBackground(color);
    }
  }
}
