class PlaceHold {
  public OutputStream getOutputStream() throws IOException {
    if (isReferenceOrProxy()) {
      return getReferencedOrProxied().getOutputStream();
    }
    if (isExists()) {
      throw new ImmutableResourceException();
    }
    return new PropertyOutputStream(getProject(), getName());
  }
}
