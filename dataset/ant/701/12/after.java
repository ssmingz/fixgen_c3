class PlaceHold {
  public void setReferenceFiles(final Path path) throws TaskException {
    if (null == m_referenceFiles) {
      m_referenceFiles = new Path();
    }
    m_referenceFiles.add(path);
  }
}
