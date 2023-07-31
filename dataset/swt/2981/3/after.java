class Tracker {
  public Tracker(Composite parent, int style) {
    super(parent, checkStyle(style));
    this.parent = parent;
    display = parent.getDisplay();
  }
}
