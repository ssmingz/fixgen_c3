class PlaceHold {
  void register() {
    super.register();
    WidgetTable.put(OS.gtk_tree_view_get_selection(handle), this);
  }
}
