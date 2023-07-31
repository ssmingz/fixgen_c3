class PlaceHold {
  private void doOperationOnDirs(final Map dirMap) {
    final Iterator dirs = dirMap.values().iterator();
    int count = 0;
    while (dirs.hasNext()) {
      final String pathname = ((String) (dirs.next()));
      final File dir = new File(pathname);
      if (!dir.exists()) {
        if (!dir.mkdirs()) {
          final String message = REZ.getString("copy.dircopy.error", dir.getAbsolutePath());
          getContext().error(message);
        } else {
          count++;
        }
      }
    }
    if (count > 0) {
      displayDirCopyNotice(count);
    }
  }
}
