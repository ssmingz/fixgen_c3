class PlaceHold {
  public synchronized String[] getIncludedDirectories() {
    if (dirsIncluded == null) {
      throw new IllegalStateException("Must call scan() first");
    }
    String[] directories = new String[dirsIncluded.size()];
    dirsIncluded.copyInto(directories);
    Arrays.sort(directories);
    return directories;
  }
}
