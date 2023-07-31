class PlaceHold {
  public void setVisible(boolean visible) {
    checkWidget();
    if (this.visible == visible) {
      return;
    }
    if (visible) {
      sendEvent(Show);
      if (isDisposed()) {
        return;
      }
    }
    this.visible = visible;
    double width = ((image != null) && visible) ? handle.size().width + BORDER : 0;
    item.setLength(width);
    if (!visible) {
      sendEvent(Hide);
    }
  }
}
