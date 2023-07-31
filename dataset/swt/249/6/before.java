class Shell {
  public Shell(Display display) {
    this(display, OS.IsWinCE ? SWT.NULL : SWT.SHELL_TRIM);
  }
}
