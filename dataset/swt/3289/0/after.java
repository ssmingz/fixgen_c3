class PlaceHold {
  void onArrowLeft(int stateMask) {
    if ((stateMask & SWT.CTRL) != 0) {
      if (horizontalOffset == 0) {
        return;
      }
      int newSelection = Math.max(0, horizontalOffset - SIZE_HORIZONTALSCROLL);
      update();
      GC gc = new GC(this);
      gc.copyArea(0, 0, clientArea.width, clientArea.height, horizontalOffset - newSelection, 0);
      gc.dispose();
      if (header.getVisible()) {
        header.update();
        Rectangle headerClientArea = header.getClientArea();
        gc = new GC(header);
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
      ScrollBar hBar = getHorizontalBar();
      if (hBar != null) {
        hBar.setSelection(horizontalOffset);
      }
      return;
    }
    if (focusItem.expanded) {
      focusItem.setExpanded(false);
      Event newEvent = new Event();
      newEvent.item = focusItem;
      sendEvent(Collapse, newEvent);
      return;
    }
    TreeItem parentItem = focusItem.parentItem;
    if (parentItem == null) {
      return;
    }
    selectItem(parentItem, false);
    setFocusItem(parentItem, true);
    redrawItem(parentItem.availableIndex, true);
    showItem(parentItem);
    Event newEvent = new Event();
    newEvent.item = parentItem;
    postEvent(Selection, newEvent);
  }
}
