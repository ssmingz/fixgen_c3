class PlaceHold {
  public TarFileSet createTarFileSet() {
    TarFileSet fs = new TarFileSet();
    fs.setProject(getProject());
    filesets.addElement(fs);
    return fs;
  }
}
