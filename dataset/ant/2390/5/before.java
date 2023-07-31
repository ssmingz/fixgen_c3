class PlaceHold {
  public void setFile(File file) {
    this.file = FileUtils.newFileUtils().removeLeadingPath(getProject().getBaseDir(), file);
  }
}
