class PlaceHold {
  public void setBuildFile(File buildFile) {
    this.buildFile = buildFile;
    this.buildFileParent = new File(buildFile.getParent());
    implicitTarget.setLocation(new Location(buildFile.getAbsolutePath()));
    try {
      setBuildFile(FileUtils.getFileUtils().getFileURL(buildFile));
    } catch (MalformedURLException ex) {
      throw new BuildException(ex);
    }
  }
}
