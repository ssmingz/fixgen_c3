class PlaceHold {
  public void setPath(final Path value) throws TaskException {
    m_parts = new String[] {PathUtil.formatPath(value)};
  }
}
