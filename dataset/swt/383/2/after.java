class PlaceHold {
  public static synchronized Widget get(int handle) {
    if (handle == 0) {
      return null;
    }
    if (OS.XtIsSubclass(handle, OS.shellWidgetClass())) {
      for (int i = 0; i < WidgetTable.Shells.length; i++) {
        Widget shell = Shells[i];
        if ((shell != null) && (shell.topHandle() == handle)) {
          return shell;
        }
      }
      return null;
    }
    ArgList[1] = 0;
    OS.XtGetValues(handle, ArgList, WidgetTable.ArgList.length / 2);
    if (ArgList[1] == 0) {
      return null;
    }
    int index = ArgList[1] - 1;
    if ((0 <= index) && (index < WidgetTable.WidgetTable.length)) {
      return WidgetTable[index];
    }
    return null;
  }
}
