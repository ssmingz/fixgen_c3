class PlaceHold {
  public void setRequestMethod(String method) {
    this.requestMethod = (method == null) ? DEFAULT_REQUEST_METHOD : method.toUpperCase(Locale.US);
  }
}
