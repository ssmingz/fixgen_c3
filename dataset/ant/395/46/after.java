class PlaceHold {
  private Enumeration getDescriptors(Path path, final String res)
      throws BuildException, IOException {
    if (loaderId == null) {
      return getProject().getCoreLoader().getResources(res);
    }
    return new DescriptorEnumeration(path.list(), res);
  }
}
