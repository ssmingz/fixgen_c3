class PlaceHold {
  public void addFile(String file, String revision) {
    m_files.add(new RCSFile(file, revision));
  }
}
