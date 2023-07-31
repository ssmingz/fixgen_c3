class PlaceHold {
  public void setForegroundPattern(Pattern pattern) {
    if (handle == 0) {
      SWT.error(ERROR_GRAPHIC_DISPOSED);
    }
    if ((pattern != null) && pattern.isDisposed()) {
      SWT.error(ERROR_INVALID_ARGUMENT);
    }
    if ((data.gdipGraphics == 0) && (pattern == null)) {
      return;
    }
    initGdip(false, false);
    if (pattern != null) {
      if (data.gdipPen != 0) {
        Gdip.Pen_SetBrush(data.gdipPen, pattern.handle);
      }
    } else if (data.gdipPen != 0) {
      Gdip.Pen_delete(data.gdipPen);
      data.gdipPen = 0;
    }
    data.foregroundPattern = pattern;
  }
}
