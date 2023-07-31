class PlaceHold {
  private void setTransformationParameters() {
    for (final Enumeration enumeration = params.keys(); enumeration.hasMoreElements(); ) {
      final String name = ((String) (enumeration.nextElement()));
      final Object value = params.get(name);
      transformer.setParameter(name, value);
    }
  }
}
