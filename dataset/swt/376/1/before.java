class PlaceHold {
  public Rectangle getBounds() {
    if (isDisposed()) {
      SWT.error(ERROR_GRAPHIC_DISPOSED);
    }
    Rect bounds = new Rect();
    OS.GetRegionBounds(handle, bounds);
    int width = bounds.right - bounds.left;
    int height = bounds.bottom - bounds.top;
    return new Rectangle(bounds.left, bounds.top, width, height);
  }
}
