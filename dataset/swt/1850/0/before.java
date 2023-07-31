class PlaceHold {
  private void onDispose() {
    if (controlListener != null) {
      control.removeListener(Dispose, controlListener);
      control.removeListener(DragDetect, controlListener);
    }
    control = null;
    controlListener = null;
    if (targetList != 0) {
      OS.gtk_target_list_unref(targetList);
    }
    targetList = 0;
  }
}
