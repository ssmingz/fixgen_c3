class PlaceHold {
  public Point computeSize(int wHint, int hHint, boolean changed) {
    Point size = super.computeSize(wHint, hHint, changed);
    if ((wHint == SWT.DEFAULT) && (items.length > 0)) {
      NSSize minSize = ((NSTabView) (view)).minimumSize();
      Rectangle trim = computeTrim(0, 0, ((int) (Math.ceil(minSize.width))), 0);
      size.x = Math.max(trim.width, size.x);
    }
    return size;
  }
}
