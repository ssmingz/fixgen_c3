class PlaceHold {
  void onScrollHorizontal(Event event) {
    int newSelection = getHorizontalBar().getSelection();
    update();
    if (itemsCount > 0) {
      GC gc = new GC(this);
      gc.copyArea(0, 0, clientArea.width, clientArea.height, horizontalOffset - newSelection, 0);
      gc.dispose();
    } else {
      redraw();
    }
    if ((drawCount <= 0) && header.isVisible()) {
      header.update();
      Rectangle headerClientArea = header.getClientArea();
      GC gc = new GC(header);
      gc.copyArea(
          0,
          0,
          headerClientArea.width,
          headerClientArea.height,
          horizontalOffset - newSelection,
          0);
      gc.dispose();
    }
    horizontalOffset = newSelection;
  }
}
