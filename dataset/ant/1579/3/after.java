class PlaceHold {
  protected String replaceReferences(String source) throws TaskException {
    ArrayList v = reg.getGroups(source);
    result.setLength(0);
    for (int i = 0; i < to.length; i++) {
      if (to[i] == '\\') {
        if ((++i) < to.length) {
          int value = Character.digit(to[i], 10);
          if (value > (-1)) {
            result.append(((String) (v.get(value))));
          } else {
            result.append(to[i]);
          }
        } else {
          result.append('\\');
        }
      } else {
        result.append(to[i]);
      }
    }
    return result.toString();
  }
}
