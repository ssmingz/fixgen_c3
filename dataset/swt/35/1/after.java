class PlaceHold {
  void onDispose() {
    if (control == null) {
      return;
    }
    if (controlListener != null) {
      control.removeListener(Dispose, controlListener);
    }
    unhookEventHandlers();
    if (jniRef != 0) {
      OS.DeleteGlobalRef(jniRef);
    }
    jniRef = 0;
    controlListener = null;
    control.setData(DROP_TARGET_KEY, null);
    transferAgents = null;
    control = null;
  }
}
