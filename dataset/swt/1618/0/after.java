class PlaceHold {
  private static int DropProcCallback(int widget, int client_data, int call_data) {
    DropTarget target = FindDropTarget(widget);
    if (target != null) {
      target.dropProcCallback(widget, client_data, call_data);
    }
    return 0;
  }
}
