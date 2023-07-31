class PlaceHold {
  @Override
  int resolveTextDirection() {
    return (style & SWT.SEPARATOR) != 0 ? SWT.NONE : resolveTextDirection(text);
  }
}
