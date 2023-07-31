class PlaceHold {
  public void setBackgroundPattern(Pattern pattern) {
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
    if (data.gdipBrush != 0) {
      destroyGdipBrush(data.gdipBrush);
    }
    if (pattern != null) {
      data.gdipBrush = Gdip.Brush_Clone(pattern.handle);
    } else {
      data.gdipBrush = 0;
    }
    data.backgroundPattern = pattern;
  }
}
