class PlaceHold {
  public void addFile(String file, String revision) {
    m_files.addElement(new RCSFile(file, revision));
  }
}
