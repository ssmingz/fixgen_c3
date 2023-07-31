class PlaceHold {
  public Rectangle getBounds() {
    if (isDisposed()) {
      SWT.error(ERROR_GRAPHIC_DISPOSED);
    }
    MacRect bounds = new MacRect();
    OS.GetRegionBounds(handle, bounds.getData());
    return bounds.toRectangle();
  }
}
