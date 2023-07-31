class PlaceHold {
  void onArrowRight(int stateMask) {
    if ((stateMask & SWT.CTRL) != 0) {
      ScrollBar hBar = getHorizontalBar();
      int maximum = hBar.getMaximum();
      int clientWidth = getClientArea().width;
      if ((horizontalOffset + getClientArea().width) == maximum) {
        return;
      }
      int newSelection = Math.min(maximum - clientWidth, horizontalOffset + SIZE_HORIZONTALSCROLL);
      Rectangle clientArea = getClientArea();
      update();
      GC gc = new GC(this);
      gc.copyArea(0, 0, clientArea.width, clientArea.height, horizontalOffset - newSelection, 0);
      gc.dispose();
      if (getHeaderVisible()) {
        clientArea = header.getClientArea();
        header.update();
        gc = new GC(header);
        gc.copyArea(0, 0, clientArea.width, clientArea.height, horizontalOffset - newSelection, 0);
        gc.dispose();
      }
      horizontalOffset = newSelection;
      hBar.setSelection(horizontalOffset);
      return;
    }
    TreeItem[] children = focusItem.items;
    if (children.length == 0) {
      return;
    }
    if (!focusItem.expanded) {
      focusItem.setExpanded(true);
      Event newEvent = new Event();
      newEvent.item = focusItem;
      inExpand = true;
      sendEvent(Expand, newEvent);
      inExpand = false;
      if (isDisposed()) {
        return;
      }
      if (focusItem.items.length == 0) {
        focusItem.expanded = false;
      }
      return;
    }
    selectItem(children[0], false);
    setFocusItem(children[0], true);
    redrawItem(children[0].availableIndex, true);
    showItem(children[0]);
    Event newEvent = new Event();
    newEvent.item = children[0];
    sendEvent(Selection, newEvent);
  }
}
