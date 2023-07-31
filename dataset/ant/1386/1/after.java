class PlaceHold {
  public TarFileSet createTarFileSet() {
    final TarFileSet fs = new TarFileSet();
    fs.setProject(getProject());
    filesets.addElement(fs);
    return fs;
  }
}
