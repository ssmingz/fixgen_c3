class PlaceHold {
  boolean translateTraversal(int key, XKeyEvent xEvent) {
    if ((style & SWT.EMBEDDED) != 0) {
      return false;
    }
    return super.translateTraversal(key, xEvent);
  }
}
