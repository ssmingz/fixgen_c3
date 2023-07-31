class PlaceHold {
  public Rectangle getBounds() {
    if (isDisposed()) {
      SWT.error(ERROR_GRAPHIC_DISPOSED);
    }
    PhImage_t image = new PhImage_t();
    OS.memmove(image, handle, sizeof);
    return new Rectangle(0, 0, image.size_w, image.size_h);
  }
}
