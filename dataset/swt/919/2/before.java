class PlaceHold {
  public void setTextAntialias(int antialias) {
    if (handle == 0) {
      SWT.error(ERROR_GRAPHIC_DISPOSED);
    }
    int textMode = 0;
    switch (antialias) {
      case SWT.DEFAULT:
        textMode = Gdip.TextRenderingHintSystemDefault;
        break;
      case SWT.OFF:
        textMode = Gdip.TextRenderingHintSingleBitPerPixelGridFit;
        break;
      case SWT.ON:
        int[] type = new int[1];
        OS.SystemParametersInfo(SPI_GETFONTSMOOTHINGTYPE, 0, type, 0);
        if (type[0] == OS.FE_FONTSMOOTHINGCLEARTYPE) {
          textMode = Gdip.TextRenderingHintClearTypeGridFit;
        } else {
          textMode = Gdip.TextRenderingHintAntiAliasGridFit;
        }
        break;
      default:
        SWT.error(ERROR_INVALID_ARGUMENT);
    }
    initGdip(false, false);
    Gdip.Graphics_SetTextRenderingHint(data.gdipGraphics, textMode);
  }
}
