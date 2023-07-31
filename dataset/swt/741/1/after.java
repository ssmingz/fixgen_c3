class PlaceHold {
  public void setBackground(Color color) {
    checkWidget();
    super.setBackground(color != null ? color : getDisplay().getSystemColor(COLOR_LIST_BACKGROUND));
    redraw();
  }
}
