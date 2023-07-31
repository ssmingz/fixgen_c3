class DetectIgnoredCodeClassVisitor {
  public DetectIgnoredCodeClassVisitor(
      ClassVisitor cv, boolean ignoreTrivial, Set<String> ignoreAnnotations) {
    super(ASM4, cv);
    this.ignoreTrivial = ignoreTrivial;
    this.ignoreAnnotations = ignoreAnnotations;
  }
}
