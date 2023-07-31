class PlaceHold {
  public void setFile(File file) {
    this.file = FILE_UTILS.removeLeadingPath(getProject().getBaseDir(), file);
  }
}
