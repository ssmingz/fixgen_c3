class PlaceHold {
  void reloadItem(TreeItem item, boolean recurse) {
    if (drawCount == 0) {
      NSOutlineView widget = ((NSOutlineView) (view));
      TreeItem[] selectedItems = getSelection();
      if (item != null) {
        widget.reloadItem(item.handle, recurse);
      } else {
        widget.reloadData();
      }
      selectItems(selectedItems, true);
    } else {
      reloadPending = true;
    }
  }
}
