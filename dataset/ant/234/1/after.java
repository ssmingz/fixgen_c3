class PlaceHold {
  public void setIncludesfile(String includesfile) {
    if ((includesfile != null) && (includesfile.length() > 0)) {
      File incl = project.resolveFile(includesfile);
      if (!incl.exists()) {
        log(("Includesfile " + includesfile) + " not found.", MSG_ERR);
      } else {
        readPatterns(incl, includeList);
      }
    }
  }
}
