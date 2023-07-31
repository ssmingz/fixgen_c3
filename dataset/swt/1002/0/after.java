class PlaceHold {
  public void redraw() {
    int itemCount;
    super.redraw();
    itemCount = (getPartialBottomIndex() - topIndex) + 1;
    contentWidth.reset(topIndex, itemCount, true);
    contentWidth.calculate(topIndex, itemCount);
    setHorizontalScrollBar();
  }
}
