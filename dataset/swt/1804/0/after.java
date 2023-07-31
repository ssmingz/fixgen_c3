class PlaceHold {
  public void setForeground(Color color) {
    checkWidget();
    super.setForeground(color);
    if (text != null) {
      text.setForeground(color);
    }
  }
}
