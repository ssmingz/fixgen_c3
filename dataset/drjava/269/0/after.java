class PlaceHold {
  public static FileFilter regexpCanonicalCaseFileFilter(String regexp) {
    return asFileFilter(regexpCanonicalCaseFilePredicate(regexp));
  }
}
