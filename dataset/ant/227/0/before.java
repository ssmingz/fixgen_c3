class PlaceHold {
  public String[] getIncludePatterns(final TaskContext context) throws TaskException {
    readFiles(context);
    return makeArray(m_includeList, context);
  }
}
