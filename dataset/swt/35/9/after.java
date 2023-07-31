class PlaceHold {
  void onDispose() {
    if (control == null) {
      return;
    }
    if (controlListener != null) {
      control.removeListener(Dispose, controlListener);
    }
    controlListener = null;
    control.setData(DROP_TARGET_KEY, null);
    transferAgents = null;
    control = null;
  }
}
