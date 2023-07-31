class PlaceHold {
  public void moveBelow(Control control) {
    checkWidget();
    if (parent == null) {
      return;
    }
    if (control != null) {
      if (control.isDisposed()) {
        error(ERROR_INVALID_ARGUMENT);
      }
      if (parent != control.parent) {
        return;
      }
    }
    int index;
    int parentHandle = parent.parentingHandle();
    int children = OS.Panel_Children(parentHandle);
    if (control != null) {
      index = Math.max(0, OS.UIElementCollection_IndexOf(children, control.topHandle()) - 1);
    } else if (parentHandle != parent.handle) {
      index = 1;
    } else {
      index = 0;
    }
    int topHandle = topHandle();
    if (OS.UIElementCollection_IndexOf(children, topHandle) > index) {
      OS.UIElementCollection_Remove(children, topHandle);
      OS.UIElementCollection_Insert(children, index, topHandle);
    }
    OS.GCHandle_Free(children);
  }
}
