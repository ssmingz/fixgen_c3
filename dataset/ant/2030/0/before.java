class PlaceHold {
  public void addFile(String file, String revision, String previousRevision) {
    m_files.add(new RCSFile(file, revision, previousRevision));
  }
}
