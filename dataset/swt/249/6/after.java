class Shell {
  public Shell(Display display) {
    this(display, OS.IsWinCE ? SWT.NONE : SWT.SHELL_TRIM);
  }
}
