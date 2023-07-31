class PlaceHold {
  public static String replaceProperties(Project project, String value, Hashtable keys)
      throws BuildException {
    StringBuffer sb = new StringBuffer();
    int i = 0;
    int prev = 0;
    int pos;
    while ((pos = value.indexOf("$", prev)) >= 0) {
      if (pos > 0) {
        sb.append(value.substring(prev, pos));
      }
      if (pos == (value.length() - 1)) {
        sb.append('$');
        prev = pos + 1;
      } else if (value.charAt(pos + 1) != '{') {
        sb.append(value.charAt(pos + 1));
        prev = pos + 2;
      } else {
        int endName = value.indexOf('}', pos);
        if (endName < 0) {
          throw new BuildException("Syntax error in prop: " + value);
        }
        String n = value.substring(pos + 2, endName);
        if (!keys.containsKey(n)) {
          project.log(("Property ${" + n) + "} has not been set", MSG_VERBOSE);
        }
        String v = (keys.containsKey(n)) ? ((String) (keys.get(n))) : ("${" + n) + "}";
        sb.append(v);
        prev = endName + 1;
      }
    }
    if (prev < value.length()) {
      sb.append(value.substring(prev));
    }
    return sb.toString();
  }
}
