class PlaceHold {
  private boolean generateChecksums() throws TaskException {
    boolean checksumMatches = true;
    final Enumeration includes = m_includeFileMap.keys();
    while (includes.hasMoreElements()) {
      final File src = ((File) (includes.nextElement()));
      final String message = (("Calculating " + m_algorithm) + " checksum for ") + src;
      getContext().info(message);
      checksumMatches = z(src, checksumMatches);
    }
    return checksumMatches;
  }
}
