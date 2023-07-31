class PlaceHold {
  public void setTransform(Transform transform) {
    if (handle == 0) {
      SWT.error(ERROR_GRAPHIC_DISPOSED);
    }
    if ((transform != null) && transform.isDisposed()) {
      SWT.error(ERROR_INVALID_ARGUMENT);
    }
    if ((data.gdipGraphics == 0) && (transform == null)) {
      return;
    }
    initGdip();
    long identity = identity();
    if (transform != null) {
      Gdip.Matrix_Multiply(identity, transform.handle, MatrixOrderPrepend);
    }
    Gdip.Graphics_SetTransform(data.gdipGraphics, identity);
    Gdip.Matrix_delete(identity);
    data.state &= ~DRAW_OFFSET;
  }
}
