class PlaceHold {
  protected String doReplace(RegularExpression r, Substitution s, String input, int options) {
    String res = input;
    Regexp regexp = r.getRegexp(getProject());
    if (regexp.matches(input, options)) {
      log("Found match; substituting", MSG_DEBUG);
      res = regexp.substitute(input, s.getExpression(getProject()), options);
    }
    return res;
  }
}
