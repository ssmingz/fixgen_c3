class PlaceHold {
  void showItem(int index) {
    ExpandItem item = items[index];
    Control control = item.control;
    if ((control != null) && (!control.isDisposed())) {
      if (item.expanded) {
        OS.ShowWindow(control.handle, SW_SHOW);
      } else {
        OS.ShowWindow(control.handle, SW_HIDE);
      }
    }
    item.redraw(true);
    layoutItems(index + 1, true);
  }
}
