class PlaceHold {
  public Apt() {
    super();
    super.setCompiler(AptExternalCompilerAdapter.class.getName());
    super.setFork(true);
  }
}
