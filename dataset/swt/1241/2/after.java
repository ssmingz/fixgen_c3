class PlaceHold {
  public void moveAbove(Control control) {
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
      index = OS.UIElementCollection_IndexOf(children, control.topHandle());
    } else {
      index = OS.UIElementCollection_Count(children) - 1;
    }
    int topHandle = topHandle();
    if (OS.UIElementCollection_IndexOf(children, topHandle) < index) {
      OS.UIElementCollection_Remove(children, topHandle);
      OS.UIElementCollection_Insert(children, index, topHandle);
    }
    OS.GCHandle_Free(children);
  }
}
