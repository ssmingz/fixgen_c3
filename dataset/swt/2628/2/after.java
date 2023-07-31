class PlaceHold {
  @Override
  int resolveTextDirection() {
    return (style & SWT.ARROW) != 0 ? SWT.NONE : BidiUtil.resolveTextDirection(text);
  }
}
