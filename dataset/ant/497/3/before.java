class PlaceHold {
  public void setItems(String itemString) {
    project.log(
        "The items attribute is deprecated. " + "Please use the includes attribute.", MSG_WARN);
    if (((itemString == null) || itemString.equals("*")) || itemString.equals(".")) {
      createInclude().setName("**");
    } else {
      StringTokenizer tok = new StringTokenizer(itemString, ", ");
      while (tok.hasMoreTokens()) {
        String pattern = tok.nextToken().trim();
        if (pattern.length() > 0) {
          createInclude().setName(pattern + "/**");
        }
      }
    }
  }
}
