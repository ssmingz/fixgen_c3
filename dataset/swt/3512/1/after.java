class PlaceHold {
  void checkItems() {
    if (!reloadPending) {
      return;
    }
    reloadPending = false;
    TreeItem[] selectedItems = getSelection();
    ((NSOutlineView) (view)).reloadData();
    selectItems(selectedItems, true);
  }
}
