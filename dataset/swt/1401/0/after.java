class PlaceHold {
  public void dispose() {
    if (isDisposed()) {
      return;
    }
    int startIndex = -1;
    int endIndex = -1;
    Tree2 parent = this.parent;
    int index = getIndex();
    if (isAvailable()) {
      if (isLastChild() && (index > 0)) {
        if (parentItem != null) {
          startIndex = parentItem.getItems()[index - 1].availableIndex;
        } else {
          startIndex = parent.getItems()[index - 1].availableIndex;
        }
      } else {
        startIndex = availableIndex;
      }
      endIndex = parent.availableItems.length - 1;
    }
    TreeItem2 focusItem = parent.focusItem;
    if ((focusItem != null) && focusItem.hasAncestor(this)) {
      parent.setFocusItem(this, false);
      parent.reassignFocus();
    }
    if (parentItem != null) {
      parentItem.removeItem(this, index);
    }
    dispose(true);
    if (startIndex != (-1)) {
      parent.redrawItems(startIndex, endIndex);
    }
  }
}
