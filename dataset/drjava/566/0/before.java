class PlaceHold {
  public boolean containsCompiler(File f) {
    if (f.isFile()) {
      try {
        JarFile jf = new JarFile(f);
        return (jf.getJarEntry("edu/rice/cs/nextgen2/classloader/Runner.class") != null)
            && (jf.getJarEntry("edu/rice/cs/nextgen2/compiler/Main.class") != null);
      } catch (IOException ioe) {
        return false;
      }
    } else if (f.isDirectory()) {
      return new File(f, "edu/rice/cs/nextgen2/classloader/Runner.class").exists()
          && new File(f, "edu/rice/cs/nextgen2/compiler/Main.class").exists();
    }
    return false;
  }
}
