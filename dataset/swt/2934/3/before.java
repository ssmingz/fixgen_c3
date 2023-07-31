class PlaceHold {
  public void setTransform(Transform transform) {
    if (handle == 0) {
      SWT.error(ERROR_GRAPHIC_DISPOSED);
    }
    if ((transform != null) && transform.isDisposed()) {
      SWT.error(ERROR_INVALID_ARGUMENT);
    }
    initGdip(false, false);
    if (transform != null) {
      Gdip.Graphics_SetTransform(data.gdipGraphics, transform.handle);
    } else {
      int identity = Gdip.Matrix_new(1, 0, 0, 1, 0, 0);
      Gdip.Graphics_SetTransform(data.gdipGraphics, identity);
      Gdip.Matrix_delete(identity);
    }
  }
}
