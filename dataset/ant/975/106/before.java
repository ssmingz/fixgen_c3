class PlaceHold {
  public void execute() throws TaskException {
    final FileObject[] files = m_files.listFiles(getContext());
    for (int i = 0; i < files.length; i++) {
      FileObject file = files[i];
      getLogger().info(file.toString());
    }
  }
}
