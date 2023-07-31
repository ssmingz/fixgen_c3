class PlaceHold {
  public void addFile(String file, String revision, String previousRevision) {
    m_files.addElement(new RCSFile(file, revision, previousRevision));
  }
}
