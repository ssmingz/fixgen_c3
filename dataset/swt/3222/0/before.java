class PlaceHold {
  public void setBackgroundPattern(Pattern pattern) {
    if (handle == 0) {
      SWT.error(ERROR_GRAPHIC_DISPOSED);
    }
    if ((pattern != null) && pattern.isDisposed()) {
      SWT.error(ERROR_INVALID_ARGUMENT);
    }
    initCairo();
    data.backgroundPattern = pattern;
  }
}
