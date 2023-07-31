class PlaceHold {
  public void setText(String value) {
    checkWidget();
    if (value == null) {
      error(ERROR_NULL_ARGUMENT);
    }
    if (value.equals(text)) {
      return;
    }
    super.setText(value);
    GC gc = new GC(parent);
    textWidth = gc.textExtent(value).x;
    gc.dispose();
    parent.redraw(x, 0, width, parent.getHeaderHeight(), true);
  }
}
