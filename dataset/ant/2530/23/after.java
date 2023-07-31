class PlaceHold {
  private void findNextResource() {
    URL url = null;
    while ((pathElementsIndex < pathComponents.size()) && (url == null)) {
      File pathComponent = ((File) (pathComponents.get(pathElementsIndex)));
      url = getResourceURL(pathComponent, this.resourceName);
      pathElementsIndex++;
    }
    this.nextResource = url;
  }
}
