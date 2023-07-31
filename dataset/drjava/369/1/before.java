class PlaceHold {
  public static File[] attemptListFiles(File f, Predicate<? super File> filter) {
    return attemptListFiles(f, asFileFilter(filter));
  }
}
