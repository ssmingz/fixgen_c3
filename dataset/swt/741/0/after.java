class PlaceHold {
  public void setForeground(Color color) {
    checkWidget();
    super.setForeground(color != null ? color : getDisplay().getSystemColor(COLOR_LIST_FOREGROUND));
    redraw();
  }
}
