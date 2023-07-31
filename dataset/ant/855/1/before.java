class PlaceHold {
  public Object convert(String value, Class type) throws ExecutionException {
    try {
      return new URL(value);
    } catch (MalformedURLException e) {
      throw new ExecutionException(e);
    }
  }
}
