class PlaceHold {
  private static int DragProcCallback(int widget, int client_data, int call_data) {
    DropTarget target = FindDropTarget(widget);
    if (target == null) {
      return 0;
    }
    return target.dragProcCallback(widget, client_data, call_data);
  }
}
