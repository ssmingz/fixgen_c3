class PlaceHold {
  public String getKey() {
    if (name == null) {
      return null;
    }
    return name.toLowerCase(Locale.ENGLISH);
  }
}
