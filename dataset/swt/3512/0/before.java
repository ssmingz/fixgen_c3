class PlaceHold {
  void reloadItem(TreeItem item, boolean recurse) {
    if (drawCount == 0) {
      NSOutlineView widget = ((NSOutlineView) (view));
      if (item != null) {
        widget.reloadItem(item.handle, recurse);
      } else {
        widget.reloadData();
      }
    } else {
      reloadPending = true;
    }
  }
}
