class PlaceHold {
  public String[] getExcludePatterns(final TaskContext context) throws TaskException {
    readFiles(context);
    return makeArray(m_excludeList, context);
  }
}
