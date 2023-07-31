class PlaceHold {
  void onDispose() {
    if (control == null) {
      return;
    }
    OS.g_signal_handler_disconnect(control.handle, drag_motion_handler);
    OS.g_signal_handler_disconnect(control.handle, drag_leave_handler);
    OS.g_signal_handler_disconnect(control.handle, drag_data_received_handler);
    OS.g_signal_handler_disconnect(control.handle, drag_drop_handler);
    if (transferAgents.length != 0) {
      OS.gtk_drag_dest_unset(control.handle);
    }
    transferAgents = null;
    if (controlListener != null) {
      control.removeListener(Dispose, controlListener);
    }
    control.setData(DROP_TARGET_KEY, null);
    control = null;
    controlListener = null;
  }
}
