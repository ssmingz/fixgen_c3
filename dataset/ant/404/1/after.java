class PlaceHold {
  public String[] getIncludedDirectories() {
    if (dirsIncluded == null) {
      throw new IllegalStateException();
    }
    String[] directories = new String[dirsIncluded.size()];
    dirsIncluded.copyInto(directories);
    Arrays.sort(directories);
    return directories;
  }
}
