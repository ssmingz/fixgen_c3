class PlaceHold {
  public void setFont(Font value) {
    checkWidget();
    if ((value != null) && value.isDisposed()) {
      SWT.error(ERROR_INVALID_ARGUMENT);
    }
    if (font == value) {
      return;
    }
    if ((value != null) && value.equals(font)) {
      return;
    }
    Rectangle bounds = getBounds();
    int oldRightX = bounds.x + bounds.width;
    font = value;
    GC gc = new GC(parent);
    gc.setFont(getFont());
    fontHeight = gc.getFontMetrics().getHeight();
    computeDisplayTexts(gc);
    recomputeTextWidths(gc);
    gc.dispose();
    if (parent.columns.length == 0) {
      bounds = getBounds();
      int newRightX = bounds.x + bounds.width;
      parent.updateHorizontalBar(newRightX, newRightX - oldRightX);
    }
    redrawItem();
  }
}
