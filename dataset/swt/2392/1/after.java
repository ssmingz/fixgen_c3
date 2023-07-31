class PlaceHold {
  @Override
  public Point computeSize(int wHint, int hHint, boolean changed) {
    checkWidget();
    int count = data.size();
    GC gc = new GC(this);
    int titleWidth = gc.stringExtent(title).x;
    Point valueSize = gc.stringExtent(new Integer(valueMax).toString());
    int itemWidth = 0;
    for (int i = 0; i < count; i++) {
      Object[] dataItem = data.get(i);
      String itemLabel = ((String) (dataItem[0]));
      itemWidth = Math.max(itemWidth, gc.stringExtent(itemLabel).x);
    }
    gc.dispose();
    int width =
        ((Math.max(titleWidth, (count * (itemWidth + GAP)) + GAP) + (3 * GAP)) + AXIS_WIDTH)
            + valueSize.x;
    int height =
        ((3 * GAP) + AXIS_WIDTH) + (valueSize.y * (((valueMax - valueMin) / valueIncrement) + 3));
    if (wHint != SWT.DEFAULT) {
      width = wHint;
    }
    if (hHint != SWT.DEFAULT) {
      height = hHint;
    }
    int border = getBorderWidth();
    Rectangle trim = computeTrim(0, 0, width + (border * 2), height + (border * 2));
    return new Point(trim.width, trim.height);
  }
}
