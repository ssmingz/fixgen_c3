class PlaceHold {
  void showItem(int index) {
    ExpandItem item = items[index];
    Control control = item.control;
    if ((control != null) && (!control.isDisposed())) {
      control.setVisible(item.expanded);
    }
    item.redraw();
    layoutItems(index + 1, true);
  }
}
