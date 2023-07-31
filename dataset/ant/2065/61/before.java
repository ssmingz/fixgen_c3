class PlaceHold {
  public void setTablength(int tlength) throws BuildException {
    if ((tlength < 2) || (tlength > 80)) {
      throw new BuildException("tablength must be between 2 and 80", location);
    }
    tablength = tlength;
    StringBuffer sp = new StringBuffer();
    for (int i = 0; i < tablength; i++) {
      sp.append(' ');
    }
    spaces = sp.toString();
  }
}
