class PlaceHold {
  int windowProc(int w, int client_data, int call_data, int continue_to_dispatch) {
    Widget widget = getWidget(w);
    if (widget == null) {
      return 0;
    }
    return widget.windowProc(w, client_data, call_data, continue_to_dispatch);
  }
}
