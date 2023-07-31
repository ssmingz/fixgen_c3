class PlaceHold {
  void register() {
    super.register();
    int hAdjustment = OS.gtk_range_get_adjustment(handle);
    WidgetTable.put(hAdjustment, this);
  }
}
