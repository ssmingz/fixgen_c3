class Shell {
  public Shell(Shell parent, int style) {
    this(parent != null ? parent.display : null, parent, style, 0, false);
  }
}
