class PlaceHold {
  public Point computeSize(int wHint, int hHint, boolean changed) {
    checkWidget();
    if (wHint == SWT.DEFAULT) {
      wHint = 200;
    }
    return computeNativeSize(scrolledHandle, wHint, hHint, changed);
  }
}
