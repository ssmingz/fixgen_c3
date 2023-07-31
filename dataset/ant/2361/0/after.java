class PlaceHold {
  private boolean mustSetSourceForTarget(String t) {
    if (assumeJava14()) {
      return false;
    }
    if (t.startsWith("1.")) {
      t = t.substring(2);
    }
    return ((((t.equals("1") || t.equals("2")) || t.equals("3")) || t.equals("4"))
            || (((t.equals("5") || t.equals("6")) && (!assumeJava15())) && (!assumeJava16())))
        || (t.equals("7") && (!assumeJava17()));
  }
}
