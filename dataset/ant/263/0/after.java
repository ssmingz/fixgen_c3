class PlaceHold {
  public void setInput(File input) {
    if (inputString != null) {
      throw new BuildException(
          "The \"input\" and \"inputstring\" " + "attributes cannot both be specified");
    }
    this.input = input;
    incompatibleWithSpawn = true;
  }
}
