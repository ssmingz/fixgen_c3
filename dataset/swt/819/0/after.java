class PlaceHold {
  public void setBackgroundPattern(Pattern pattern) {
    if (handle == 0) {
      SWT.error(ERROR_GRAPHIC_DISPOSED);
    }
    if ((pattern != null) && pattern.isDisposed()) {
      SWT.error(ERROR_INVALID_ARGUMENT);
    }
    if ((data.cairo == 0) && (pattern == null)) {
      return;
    }
    initCairo();
    data.backgroundPattern = pattern;
  }
}
