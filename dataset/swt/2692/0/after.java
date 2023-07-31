class PlaceHold {
  public Rectangle getBounds() {
    if (isDisposed()) {
      SWT.error(ERROR_GRAPHIC_DISPOSED);
    }
    MacRect bounds = new MacRect();
    OS.GetPixBounds(pixmap, bounds.getData());
    return bounds.toRectangle();
  }
}
