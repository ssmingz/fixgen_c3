class PlaceHold {
  public void dispose() {
    if (isDisposed()) {
      return;
    }
    Composite parent = this.parent;
    int[] argList = new int[] {OS.XmNoverrideRedirect, 0};
    OS.XtGetValues(shellHandle, argList, argList.length / 2);
    super.dispose();
    if ((parent != null) && (argList[1] != 0)) {
      Shell shell = parent.getShell();
      shell.bringToTop();
    }
  }
}
