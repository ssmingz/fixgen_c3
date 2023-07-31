class PlaceHold {
  protected Vector createUnifiedSources() {
    Vector sources = ((Vector) (filesets.clone()));
    if (jar != null) {
      FileSet sourceJar = new FileSet();
      sourceJar.setProject(getProject());
      sourceJar.setFile(jar);
      sourceJar.setDir(jar.getParentFile());
      sources.add(sourceJar);
    }
    return sources;
  }
}
