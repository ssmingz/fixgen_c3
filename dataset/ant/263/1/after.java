class PlaceHold {
  public void setInputString(String inputString) {
    if (input != null) {
      throw new BuildException(
          "The \"input\" and \"inputstring\" " + "attributes cannot both be specified");
    }
    this.inputString = inputString;
    incompatibleWithSpawn = true;
  }
}
