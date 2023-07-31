class PlaceHold {
  public boolean containsCompiler(File f) {
    return Util.exists(
        f,
        "edu/rice/cs/nextgen2/classloader/Runner.class",
        "edu/rice/cs/nextgen2/compiler/Main.class");
  }
}
