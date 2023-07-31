class PlaceHold {
  public void fillPath(Path path) {
    if (handle == 0) {
      SWT.error(ERROR_GRAPHIC_DISPOSED);
    }
    if (path.handle == 0) {
      SWT.error(ERROR_INVALID_ARGUMENT);
    }
    initGdip(false, true);
    Gdip.Graphics_FillPath(data.gdipGraphics, data.gdipBrush, path.handle);
  }
}
