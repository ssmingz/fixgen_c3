class PlaceHold {
  public boolean containsCompiler(File f) {
    if (f.isFile()) {
      try {
        JarFile jf = new JarFile(f);
        return jf.getJarEntry("org/eclipse/jdt/internal/compiler/tool/EclipseCompiler.class")
            != null;
      } catch (IOException ioe) {
        return false;
      }
    } else if (f.isDirectory()) {
      return new File(f, "org/eclipse/jdt/internal/compiler/tool/EclipseCompiler.class").exists();
    }
    return false;
  }
}
