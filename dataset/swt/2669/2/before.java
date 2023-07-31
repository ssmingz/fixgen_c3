class PlaceHold {
  void HandleSelectedItemChanged(int sender, int e) {
    if (!checkEvent(e)) {
      return;
    }
    if (ignoreSelection) {
      return;
    }
    int selectedItem = OS.TreeView_SelectedItem(handle);
    if (selectedItem == 0) {
      return;
    }
    TreeItem item = ((TreeItem) (display.getWidget(selectedItem)));
    Event event = new Event();
    event.item = item;
    postEvent(Selection, event);
  }
}
