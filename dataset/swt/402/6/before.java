class PlaceHold {
  int gtk_changed(int widget) {
    TreeItem item = getFocusItem();
    if (item != null) {
      Event event = new Event();
      event.item = item;
      postEvent(Selection, event);
    }
    return 0;
  }
}
