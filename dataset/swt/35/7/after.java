class PlaceHold {
  void onDispose() {
    if (control == null) {
      return;
    }
    if (controlListener != null) {
      Control c = control;
      while (c != null) {
        c.removeListener(Show, controlListener);
        c.removeListener(Hide, controlListener);
        c = c.getParent();
      }
      control.removeListener(Dispose, controlListener);
    }
    controlListener = null;
    control.setData(DROP_TARGET_KEY, null);
    control = null;
    transferAgents = null;
  }
}
