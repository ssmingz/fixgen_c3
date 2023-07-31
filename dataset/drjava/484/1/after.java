class PlaceHold {
  public Integer parse(String s) {
    int value;
    try {
      value = Integer.parseInt(s);
      if (value < 0) {
        throw new OptionParseException(name, s, "Must be a non-negative integer value.");
      }
      return Integer.valueOf(value);
    } catch (NumberFormatException e) {
      throw new OptionParseException(name, s, "Must be a non-negative integer value.");
    }
  }
}
