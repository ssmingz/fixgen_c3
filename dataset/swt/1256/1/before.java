class PlaceHold {
  public void dispose() {
    if (isDisposed()) {
      return;
    }
    Rectangle parentBounds = parent.getClientArea();
    int x = getX();
    Tree parent = this.parent;
    dispose(true);
    parent.redraw(x, 0, parentBounds.width - x, parentBounds.height, true);
  }
}
