class PlaceHold {
  void checkItems() {
    if (!reloadPending) {
      return;
    }
    reloadPending = false;
    ((NSOutlineView) (view)).reloadData();
  }
}
