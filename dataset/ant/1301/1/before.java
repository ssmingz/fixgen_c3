class PlaceHold {
  public void setBuildFile(File buildFile) {
    this.buildFile = buildFile;
    this.buildFileParent = new File(buildFile.getParent());
    implicitTarget.setLocation(new Location(buildFile.getAbsolutePath()));
  }
}
