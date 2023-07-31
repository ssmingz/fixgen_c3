class PlaceHold {
  public void setAntialias(int antialias) {
    if (handle == 0) {
      SWT.error(ERROR_GRAPHIC_DISPOSED);
    }
    if ((data.gdipGraphics == 0) && (antialias == SWT.DEFAULT)) {
      return;
    }
    int mode = 0;
    switch (antialias) {
      case SWT.DEFAULT:
        mode = Gdip.SmoothingModeDefault;
        break;
      case SWT.OFF:
        mode = Gdip.SmoothingModeNone;
        break;
      case SWT.ON:
        mode = Gdip.SmoothingModeAntiAlias;
        break;
      default:
        SWT.error(ERROR_INVALID_ARGUMENT);
    }
    initGdip(false, false);
    Gdip.Graphics_SetSmoothingMode(data.gdipGraphics, mode);
  }
}
