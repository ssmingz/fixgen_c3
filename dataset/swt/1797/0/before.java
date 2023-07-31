class PlaceHold {
  public void setClipping(Path path) {
    if (handle == 0) {
      SWT.error(ERROR_GRAPHIC_DISPOSED);
    }
    if ((path != null) && path.isDisposed()) {
      SWT.error(ERROR_INVALID_ARGUMENT);
    }
    initGdip(false, false);
    setClipping(0);
    if (path != null) {
      Gdip.Graphics_SetClip(data.gdipGraphics, path.handle);
    }
  }
}
