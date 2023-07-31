class PlaceHold {
  int getRightItemEdge(GC gc) {
    Rectangle trim = renderer.computeTrim(PART_HEADER, NONE, 0, 0, 0, 0);
    int x = (getSize().x - (trim.width + trim.x)) - 3;
    if (showMin) {
      x -= renderer.computeSize(PART_MIN_BUTTON, NONE, gc, DEFAULT, DEFAULT).x;
    }
    if (showMax) {
      x -= renderer.computeSize(PART_MAX_BUTTON, NONE, gc, DEFAULT, DEFAULT).x;
    }
    if (showChevron) {
      x -= renderer.computeSize(PART_CHEVRON_BUTTON, NONE, gc, DEFAULT, DEFAULT).x;
    }
    if ((topRight != null) && (topRightAlignment != SWT.FILL)) {
      Point rightSize = topRight.computeSize(DEFAULT, DEFAULT);
      x -= rightSize.x + 3;
    }
    return Math.max(0, x);
  }
}
