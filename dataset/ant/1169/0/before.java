class PlaceHold {
  public Apt() {
    super();
    super.setCompiler(AptExternalCompilerAdapter.class.getName());
    setFork(true);
  }
}
