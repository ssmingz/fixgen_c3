class PlaceHold {
  public String substitute(String input, String argument, int options) throws TaskException {
    ArrayList v = getGroups(input, options);
    StringBuffer result = new StringBuffer();
    for (int i = 0; i < argument.length(); i++) {
      char c = argument.charAt(i);
      if (c == '\\') {
        if ((++i) < argument.length()) {
          c = argument.charAt(i);
          int value = Character.digit(c, 10);
          if (value > (-1)) {
            result.append(((String) (v.get(value))));
          } else {
            result.append(c);
          }
        } else {
          result.append('\\');
        }
      } else {
        result.append(c);
      }
    }
    argument = result.toString();
    RE reg = getCompiledPattern(options);
    int sOptions = getSubsOptions(options);
    return reg.subst(input, argument, sOptions);
  }
}
