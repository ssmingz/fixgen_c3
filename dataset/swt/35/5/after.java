class PlaceHold {
  void onDispose() {
    if (control == null) {
      return;
    }
    COM.RevokeDragDrop(control.handle);
    if (controlListener != null) {
      control.removeListener(Dispose, controlListener);
    }
    controlListener = null;
    control.setData(DROP_TARGET_KEY, null);
    transferAgents = null;
    control = null;
    COM.CoLockObjectExternal(iDropTarget.getAddress(), false, true);
    this.Release();
    COM.CoFreeUnusedLibraries();
  }
}
