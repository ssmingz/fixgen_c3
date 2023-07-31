class PlaceHold {
  Rectangle itemBounds(int index) {
    Rectangle rect = getClientArea();
    GC gc = new GC(this);
    Point valueSize = gc.stringExtent(new Integer(valueMax).toString());
    gc.dispose();
    int leftX = (rect.x + (2 * GAP)) + valueSize.x;
    int bottomY = ((rect.y + rect.height) - (2 * GAP)) - valueSize.y;
    int unitWidth = ((((rect.width - (4 * GAP)) - valueSize.x) - AXIS_WIDTH) / data.size()) - GAP;
    int unitHeight =
        (((rect.height - (3 * GAP)) - AXIS_WIDTH) - (2 * valueSize.y))
            / ((valueMax - valueMin) / valueIncrement);
    Object[] dataItem = data.get(index);
    int itemValue = ((Integer) (dataItem[1])).intValue();
    int x = ((leftX + AXIS_WIDTH) + GAP) + (index * (unitWidth + GAP));
    return new Rectangle(
        x,
        (bottomY - (itemValue * unitHeight)) - AXIS_WIDTH,
        unitWidth,
        (((itemValue * unitHeight) + AXIS_WIDTH) + GAP) + valueSize.y);
  }
}
