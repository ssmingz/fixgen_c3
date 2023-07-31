class PlaceHold {
  public void dispose() {
    if (isDisposed()) {
      return;
    }
    Rectangle parentBounds = parent.getClientArea();
    int x = getX();
    Tree parent = this.parent;
    dispose(true);
    int width = parentBounds.width - x;
    parent.redraw(x, 0, width, parentBounds.height, false);
    if (parent.getHeaderVisible()) {
      parent.header.redraw(x, 0, width, parent.getHeaderHeight(), false);
    }
  }
}
