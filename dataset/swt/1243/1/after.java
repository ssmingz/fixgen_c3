class PlaceHold {
  public void removeAll() {
    checkWidget();
    if (items.length == 0) {
      return;
    }
    int lastAvailableIndex = parent.availableItemsCount - 1;
    TreeItem focusItem = parent.focusItem;
    if ((focusItem != null) && focusItem.hasAncestor(this)) {
      parent.setFocusItem(this, false);
    }
    while (items.length > 0) {
      items[0].dispose(true);
      removeItem(items[0], 0);
    }
    items = Tree.NO_ITEMS;
    expanded = false;
    if (isAvailable()) {
      parent.redrawItems(availableIndex, lastAvailableIndex, false);
    }
  }
}
