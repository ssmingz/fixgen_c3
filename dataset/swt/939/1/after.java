class PlaceHold {
  public Point computeSize(int wHint, int hHint, boolean changed) {
    checkWidget();
    return computeNativeSize(handle, wHint, hHint, changed);
  }
}
