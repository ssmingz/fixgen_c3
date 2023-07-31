class PlaceHold {
  @Override
  public String[] mapFileName(final String sourceFileName) {
    if (dirs <= 0) {
      throw new BuildException("dirs must be set to a positive number");
    }
    final char fileSep = File.separatorChar;
    final String fileSepCorrected = sourceFileName.replace('/', fileSep).replace('\\', fileSep);
    int nthMatch = fileSepCorrected.indexOf(fileSep);
    for (int n = 1; (nthMatch > (-1)) && (n < dirs); n++) {
      nthMatch = fileSepCorrected.indexOf(fileSep, nthMatch + 1);
    }
    if (nthMatch == (-1)) {
      return null;
    }
    return new String[] {sourceFileName.substring(nthMatch + 1)};
  }
}
