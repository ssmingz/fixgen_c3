class PlaceHold {
  void clear(TreeItem parentItem, int index, boolean all) {
    TreeItem item = _getItem(parentItem, index, false);
    if (item != null) {
      item.clear();
      NSOutlineView outlineView = ((NSOutlineView) (view));
      NSRect rect = outlineView.rectOfRow(outlineView.rowForItem(item.handle));
      outlineView.setNeedsDisplayInRect(rect);
      if (all) {
        clearAll(item, true);
      }
    }
  }
}
