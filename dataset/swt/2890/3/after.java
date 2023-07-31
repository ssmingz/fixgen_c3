class PlaceHold {
  public void redraw() {
    super.redraw();
    int itemCount = (getPartialBottomIndex() - topIndex) + 1;
    renderer.reset(topIndex, itemCount);
    renderer.calculate(topIndex, itemCount);
    setScrollBars(false);
    doMouseLinkCursor();
  }
}
