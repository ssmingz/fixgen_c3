class PlaceHold {
  int getLeftItemEdge(GC gc, int part) {
    Rectangle trim = renderer.computeTrim(part, NONE, 0, 0, 0, 0);
    int x = -trim.x;
    int width = 0;
    for (int i = 0; i < controls.length; i++) {
      if (((controlAlignments[i] & SWT.LEAD) != 0) && controls[i].getVisible()) {
        width += controls[i].computeSize(DEFAULT, DEFAULT).x;
      }
    }
    if (width != 0) {
      width += SPACING * 2;
    }
    x += width;
    return Math.max(0, x);
  }
}
