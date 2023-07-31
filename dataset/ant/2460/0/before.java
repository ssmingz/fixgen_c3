class PlaceHold {
  public void setJarFile(File jarfile) {
    File parent = jarfile.getParentFile();
    if (!parent.isDirectory()) {
      throw new BuildException("Jar's directory not found: " + parent);
    }
    _dir = parent;
  }
}
