class PlaceHold {
  public String[] getIncludePatterns(final TaskContext context) throws TaskException {
    return toArray(m_includeList, context);
  }
}
