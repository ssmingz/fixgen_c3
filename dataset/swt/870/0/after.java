class PlaceHold {
  boolean translateTraversal(int key, XKeyEvent xEvent) {
    if (((state & CANVAS) != 0) && ((style & SWT.EMBEDDED) != 0)) {
      return false;
    }
    return super.translateTraversal(key, xEvent);
  }
}
