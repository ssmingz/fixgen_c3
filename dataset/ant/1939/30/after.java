class PlaceHold {
  public void setSkipEmptyFilesets(final boolean skip) {
    final String message =
        getContext().getName() + " doesn\'t support the skipemptyfileset attribute";
    throw new IllegalArgumentException(message);
  }
}
