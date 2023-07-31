class PlaceHold {
  private static void printTargets(
      Project project, Vector names, Vector descriptions, String heading, int maxlen) {
    String lSep = System.getProperty("line.separator");
    String spaces = "    ";
    while (spaces.length() <= maxlen) {
      spaces += spaces;
    }
    StringBuffer msg = new StringBuffer();
    msg.append((heading + lSep) + lSep);
    for (int i = 0; i < names.size(); i++) {
      msg.append(" ");
      msg.append(names.elementAt(i));
      if (descriptions != null) {
        msg.append(spaces.substring(0, (maxlen - ((String) (names.elementAt(i))).length()) + 2));
        msg.append(descriptions.elementAt(i));
      }
      msg.append(lSep);
    }
    project.log(msg.toString(), MSG_WARN);
  }
}
