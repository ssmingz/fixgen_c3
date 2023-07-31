class PlaceHold {
  public FileName getParent() {
    final String parentPath = m_parser.getParentPath(m_absPath);
    if (parentPath == null) {
      return null;
    }
    return new DefaultFileName(m_parser, m_rootPrefix, parentPath);
  }
}
