class PlaceHold {
  public void execute() throws TaskException {
    final int count = m_fileSets.size();
    for (int i = 0; i < count; i++) {
      final FileSet fileSet = ((FileSet) (m_fileSets.get(i)));
      FileSetResult result = fileSet.getResult(getContext());
      final FileObject[] files = result.getFiles();
      final String[] paths = result.getPaths();
      for (int j = 0; j < files.length; j++) {
        final FileObject file = files[j];
        final String path = paths[j];
        getLogger().info((path + " = ") + file);
      }
    }
  }
}
