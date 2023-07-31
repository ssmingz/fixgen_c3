class PlaceHold {
  int traversalCode(int key, XKeyEvent xEvent) {
    int[] argList = new int[] {OS.XmNtraversalOn, 0};
    OS.XtGetValues(handle, argList, argList.length / 2);
    if (argList[1] == 0) {
      return 0;
    }
    int code = (SWT.TRAVERSE_RETURN | SWT.TRAVERSE_TAB_NEXT) | SWT.TRAVERSE_TAB_PREVIOUS;
    Shell shell = getShell();
    if (shell.parent != null) {
      code |= SWT.TRAVERSE_ESCAPE;
    }
    if (getNavigationType() == OS.XmNONE) {
      code |= SWT.TRAVERSE_ARROW_NEXT | SWT.TRAVERSE_ARROW_PREVIOUS;
    }
    return code;
  }
}
