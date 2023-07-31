class PlaceHold {
  public FontMetrics getFontMetrics() {
    if (handle == 0) {
      SWT.error(ERROR_GRAPHIC_DISPOSED);
    }
    TEXTMETRIC lptm = (OS.IsUnicode) ? ((TEXTMETRIC) (new TEXTMETRICW())) : new TEXTMETRICA();
    OS.GetTextMetrics(handle, lptm);
    return FontMetrics.win32_new(lptm);
  }
}
