class PlaceHold {
  public void removeAll() {
    checkWidget();
    setFocusItem(null, false);
    TreeItem2[] items = this.items;
    this.items = new TreeItem2[0];
    selectedItems = new TreeItem2[0];
    availableItems = new TreeItem2[0];
    anchorItem = insertMarkItem = lastClickedItem = null;
    for (int i = 0; i < items.length; i++) {
      items[i].dispose(false);
    }
    getVerticalBar().setMaximum(1);
    getHorizontalBar().setMaximum(1);
    redraw();
  }
}
