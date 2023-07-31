class PlaceHold {
  public static synchronized Widget remove(int handle) {
    if (handle == 0) {
      return null;
    }
    if (OS.XtIsSubclass(handle, OS.shellWidgetClass())) {
      for (int i = 0; i < WidgetTable.Shells.length; i++) {
        Widget shell = Shells[i];
        if ((shell != null) && (shell.topHandle() == handle)) {
          Shells[i] = null;
          return shell;
        }
      }
      return null;
    }
    ArgList[1] = 0;
    Widget widget = null;
    OS.XtGetValues(handle, ArgList, WidgetTable.ArgList.length / 2);
    int index = ArgList[1] - 1;
    if ((0 <= index) && (index < WidgetTable.WidgetTable.length)) {
      widget = WidgetTable[index];
      WidgetTable[index] = null;
      IndexTable[index] = FreeSlot;
      FreeSlot = index;
      ArgList[1] = 0;
      OS.XtSetValues(handle, ArgList, WidgetTable.ArgList.length / 2);
    }
    return widget;
  }
}
