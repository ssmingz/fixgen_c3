class PlaceHold {
  public void addFileset(FileSet fs) throws BuildException {
    if (fs.getProject() == null) {
      fs.setProject(getProject());
    }
    add(fs);
  }
}
