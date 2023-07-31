class DetectIgnoredCodeClassVisitor {
  public DetectIgnoredCodeClassVisitor(
      ClassVisitor cv, boolean ignoreTrivial, Set<String> ignoreAnnotations) {
    super(cv);
    this.ignoreTrivial = ignoreTrivial;
    this.ignoreAnnotations = ignoreAnnotations;
  }
}
