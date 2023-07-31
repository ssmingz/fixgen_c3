class PlaceHold {
  void register() {
    super.register();
    display.addWidget(OS.gtk_tree_view_get_selection(handle), this);
  }
}
