class PlaceHold {
  public void setFont(Font font) {
    checkWidget();
    if ((font != null) && font.isDisposed()) {
      SWT.error(ERROR_INVALID_ARGUMENT);
    }
    if ((font == null) && (this.font == null)) {
      return;
    }
    if ((font != null) && font.equals(this.font)) {
      return;
    }
    this.font = font;
    if (!parent.updateTabHeight(false)) {
      parent.updateItems();
      parent.redraw();
    }
  }
}
