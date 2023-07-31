class PlaceHold {
  public boolean containsCompiler(File f) {
    return Util.exists(f, "org/eclipse/jdt/internal/compiler/tool/EclipseCompiler.class");
  }
}
