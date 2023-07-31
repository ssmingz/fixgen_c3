class PlaceHold {
  private File resolveFile(String fileName) {
    if (rootDirectory == null) {
      return getProject().resolveFile(fileName);
    }
    return fileUtils.resolveFile(rootDirectory, fileName);
  }
}
