class PlaceHold {
  public void setExpanded(boolean value) {
    checkWidget();
    if (expanded == value) {
      return;
    }
    if (items.length == 0) {
      return;
    }
    if (parent.inExpand) {
      return;
    }
    if (value) {
      expanded = value;
      if (availableIndex == (-1)) {
        return;
      }
      TreeItem[] availableDescendents = computeAvailableDescendents();
      int descendentsCount = availableDescendents.length;
      if (availableIndex != (parent.availableItemsCount - 1)) {
        Rectangle clientArea = parent.getClientArea();
        int y = parent.getItemY(this) + parent.itemHeight;
        if ((0 < y) && (y < clientArea.height)) {
          if (parent.drawCount == 0) {
            parent.update();
            GC gc = new GC(parent);
            gc.copyArea(
                0,
                y,
                clientArea.width,
                clientArea.height - y,
                0,
                y + ((descendentsCount - 1) * parent.itemHeight));
            gc.dispose();
          }
        }
      }
      parent.makeDescendentsAvailable(this, availableDescendents);
      int rightX = 0;
      for (int i = 1; i < availableDescendents.length; i++) {
        Rectangle bounds = availableDescendents[i].getBounds();
        rightX = Math.max(rightX, bounds.x + bounds.width);
      }
      parent.updateHorizontalBar(rightX, rightX);
      parent.updateVerticalBar();
      if (availableIndex < parent.topIndex) {
        parent.topIndex += descendentsCount - 1;
        parent.getVerticalBar().setSelection(parent.topIndex);
        return;
      }
      int redrawStart = availableIndex + 1;
      int redrawEnd = (redrawStart + descendentsCount) - 2;
      parent.redrawItems(redrawStart, redrawEnd, false);
    } else {
      TreeItem[] descendents = computeAvailableDescendents();
      expanded = value;
      if (availableIndex == (-1)) {
        return;
      }
      Rectangle clientArea = parent.getClientArea();
      int y = parent.getItemY(this) + parent.itemHeight;
      int startY = y + ((descendents.length - 1) * parent.itemHeight);
      if ((y < clientArea.height) && (0 < startY)) {
        if (parent.drawCount == 0) {
          parent.update();
          GC gc = new GC(parent);
          gc.copyArea(0, startY, clientArea.width, clientArea.height - startY, 0, y);
          gc.dispose();
          int redrawY = (clientArea.height - startY) + y;
          parent.redraw(0, redrawY, clientArea.width, clientArea.height - redrawY, false);
        }
      }
      parent.makeDescendentsUnavailable(this, descendents);
      int bottomIndex = (availableIndex + descendents.length) - 1;
      if (bottomIndex < parent.topIndex) {
        parent.topIndex = (parent.topIndex - descendents.length) + 1;
        parent.getVerticalBar().setSelection(parent.topIndex);
      }
      parent.updateHorizontalBar();
      parent.updateVerticalBar();
      TreeItem focusItem = parent.focusItem;
      if (((focusItem != null) && (focusItem != this)) && focusItem.hasAncestor(this)) {
        parent.setFocusItem(this, false);
        if ((parent.style & SWT.SINGLE) != 0) {
          parent.selectItem(this, false);
        }
        Event newEvent = new Event();
        newEvent.item = this;
        parent.sendEvent(Selection, newEvent);
        if (isDisposed()) {
          return;
        }
        parent.showItem(this);
        parent.redrawItem(availableIndex, true);
      }
    }
    Rectangle bounds = getExpanderBounds();
    parent.redraw(bounds.x, bounds.y, bounds.width, bounds.height, false);
  }
}
