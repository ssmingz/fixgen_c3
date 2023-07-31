class PlaceHold {
  protected boolean isUpToDate(FileScanner[] scanners, File zipFile) throws TaskException {
    String[][] fileNames = grabFileNames(scanners);
    File[] files = grabFiles(scanners, fileNames);
    if (files.length == 0) {
      if (m_emptyBehavior.equals("skip")) {
        final String message =
            ((("Warning: skipping " + m_archiveType) + " archive ") + zipFile)
                + " because no files were included.";
        getLogger().warn(message);
        return true;
      } else if (m_emptyBehavior.equals("fail")) {
        throw new TaskException(
            ((("Cannot create " + m_archiveType) + " archive ") + zipFile)
                + ": no files were included.");
      } else {
        return createEmptyZip(zipFile);
      }
    } else {
      for (int i = 0; i < files.length; ++i) {
        if (files[i].equals(zipFile)) {
          throw new TaskException("A zip file cannot include itself");
        }
      }
      if (!zipFile.exists()) {
        return false;
      }
      final SourceFileScanner scanner = new SourceFileScanner();
      setupLogger(scanner);
      MergingMapper mm = new MergingMapper();
      mm.setTo(zipFile.getAbsolutePath());
      for (int i = 0; i < scanners.length; i++) {
        if (scanner.restrict(fileNames[i], scanners[i].getBasedir(), null, mm, getContext()).length
            > 0) {
          return false;
        }
      }
      return true;
    }
  }
}
