class PlaceHold {
  private RuntimeBindings bindArgs(
      RuntimeBindings parent, List<FormalParameter> params, Object[] args) {
    return new RuntimeBindings(parent, extractVars(params), IterUtil.make(args));
  }
}
