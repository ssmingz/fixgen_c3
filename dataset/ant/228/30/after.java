class PlaceHold {
  public void addConfiguredFile(FileName name) {
    if (name.getName() == null) {
      throw new BuildException("No name specified in nested file element");
    }
    filenames.add(name.getName());
  }
}
