class PlaceHold {
  private static RE createRE(String aPattern) {
    RE retVal = null;
    try {
      retVal = new RE(aPattern);
    } catch (RESyntaxException e) {
      System.out.println("Failed to initialise regexp expression " + aPattern);
      e.printStackTrace(System.out);
      System.exit(1);
    }
    return retVal;
  }
}
