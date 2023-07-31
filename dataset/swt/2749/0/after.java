class PlaceHold {
  public void setAdvanced(boolean advanced) {
    if (handle == 0) {
      SWT.error(ERROR_GRAPHIC_DISPOSED);
    }
    if (((data.style & SWT.MIRRORED) != 0) || device.useCairo) {
      if (!advanced) {
        setAlpha(0xff);
        setAntialias(DEFAULT);
        setBackgroundPattern(null);
        setClipping(0);
        setForegroundPattern(null);
        setInterpolation(DEFAULT);
        setTextAntialias(DEFAULT);
        setTransform(null);
      }
      return;
    }
    if (advanced && (data.cairo != 0)) {
      return;
    }
    if (advanced) {
      try {
        initCairo();
      } catch (SWTException e) {
      }
    } else {
      if (!data.disposeCairo) {
        return;
      }
      int cairo = data.cairo;
      if (cairo != 0) {
        Cairo.cairo_destroy(cairo);
      }
      data.cairo = 0;
      data.interpolation = SWT.DEFAULT;
      data.alpha = 0xff;
      data.backgroundPattern = data.foregroundPattern = null;
      data.state = 0;
      setClipping(0);
    }
  }
}
