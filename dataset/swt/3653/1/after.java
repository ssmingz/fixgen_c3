class PlaceHold {
  private void onDispose() {
    if (control == null) {
      return;
    }
    if (transferAgents != null) {
      OS.gtk_drag_dest_unset(control.handle);
      if (controlListener != null) {
        control.removeListener(Dispose, controlListener);
      }
    }
    control.setData(DROPTARGETID, null);
    control = null;
    controlListener = null;
  }
}
