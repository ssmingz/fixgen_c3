class PlaceHold {
  public boolean hasErrorsWithPositions(OpenDefinitionsDocument odd) {
    File file = null;
    try {
      file = odd.getFile();
    } catch (IllegalStateException ise) {
    } catch (FileMovedException fme) {
      file = fme.getFile();
    }
    if (file == null) {
      return false;
    }
    try {
      file = file.getCanonicalFile();
    } catch (IOException ioe) {
    }
    StartAndEndIndex saei = _filesToIndexes.get(file);
    if (saei == null) {
      return false;
    }
    if (saei.getStartPos() == saei.getEndPos()) {
      return false;
    }
    return true;
  }
}
