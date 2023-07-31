class PlaceHold {
  void onArrowLeft(int stateMask) {
    if (horizontalOffset == 0) {
      return;
    }
    int newSelection = Math.max(0, horizontalOffset - SIZE_HORIZONTALSCROLL);
    Rectangle clientArea = getClientArea();
    update();
    GC gc = new GC(this);
    gc.copyArea(0, 0, clientArea.width, clientArea.height, horizontalOffset - newSelection, 0);
    gc.dispose();
    if (header.getVisible()) {
      header.update();
      clientArea = header.getClientArea();
      gc = new GC(header);
      gc.copyArea(0, 0, clientArea.width, clientArea.height, horizontalOffset - newSelection, 0);
      gc.dispose();
    }
    horizontalOffset = newSelection;
    getHorizontalBar().setSelection(horizontalOffset);
  }
}
