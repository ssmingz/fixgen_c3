class PlaceHold {
  public void setForce(String f) {
    if ((f != null) && f.equalsIgnoreCase("yes")) {
      force = "yes";
    } else {
      force = "no";
    }
  }
}
