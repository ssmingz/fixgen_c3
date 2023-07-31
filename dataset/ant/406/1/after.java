class PlaceHold {
  public boolean accept(final FileObject file, final String path, final TaskContext context)
      throws TaskException {
    if (m_selector == null) {
      final String message = REZ.getString("notfileselector.no-selector.error");
      throw new TaskException(message);
    }
    return !m_selector.accept(file, path, context);
  }
}
