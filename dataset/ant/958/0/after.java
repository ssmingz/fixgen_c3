class PlaceHold {
  public void setForce(String f) {
    if ((f != null) && f.toLowerCase(Locale.ENGLISH).equals("yes")) {
      force = "yes";
    } else {
      force = "no";
    }
  }
}
