class PlaceHold {
  public String[] getExcludePatterns(final TaskContext context) throws TaskException {
    return toArray(m_excludeList, context);
  }
}
