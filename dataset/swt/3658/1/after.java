class PlaceHold {
  int traversalCode() {
    int code = (SWT.TRAVERSE_RETURN | SWT.TRAVERSE_TAB_NEXT) | SWT.TRAVERSE_TAB_PREVIOUS;
    Shell shell = getShell();
    if (shell.parent != null) {
      code |= SWT.TRAVERSE_ESCAPE;
    }
    return code;
  }
}
