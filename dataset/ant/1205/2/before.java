class PlaceHold {
  public boolean accept(final FileObject file, final String path, final TaskContext context)
      throws TaskException {
    if (m_selector == null) {
      throw new TaskException("notfileselector.no-selector.error");
    }
    return !m_selector.accept(file, path, context);
  }
}
