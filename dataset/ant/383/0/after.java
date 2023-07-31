class PlaceHold {
  private File resolveFile(String fileName) {
    if (rootDirectory == null) {
      return getProject().resolveFile(fileName);
    }
    return FILE_UTILS.resolveFile(rootDirectory, fileName);
  }
}
