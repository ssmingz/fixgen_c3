class PlaceHold {
  public static JavahAdapter getAdapter(String choice, ProjectComponent log) throws BuildException {
    if ((JavaEnvUtils.isKaffe() && (choice == null)) || IMPLEMENTATION_NAME.equals(choice)) {
      return new Kaffeh();
    } else if (IMPLEMENTATION_NAME.equals(choice)) {
      return new SunJavah();
    } else if (choice != null) {
      return resolveClassName(choice);
    }
    return new SunJavah();
  }
}
