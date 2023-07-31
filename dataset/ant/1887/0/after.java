class PlaceHold {
  public void addFileSet(FileSet fs) {
    add(fs);
    if (fs.getProject() == null) {
      fs.setProject(project);
    }
  }
}
