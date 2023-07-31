class PlaceHold {
  public void setJunit(String junit) {
    commandline.createClasspath().createPathElement().setLocation(junit);
  }
}
