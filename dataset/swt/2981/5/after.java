class Composite {
  public Composite(Composite parent, int style) {
    super(parent, checkStyle(style));
  }
}
