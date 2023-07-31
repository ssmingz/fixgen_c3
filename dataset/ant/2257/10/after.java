class PlaceHold {
  private boolean archiveIsUpToDate(final String[] files) throws TaskException {
    final SourceFileScanner scanner = new SourceFileScanner();
    setupLogger(scanner);
    final MergingMapper mapper = new MergingMapper();
    mapper.setTo(tarFile.getAbsolutePath());
    return scanner.restrict(files, baseDir, null, mapper, getContext()).length == 0;
  }
}
