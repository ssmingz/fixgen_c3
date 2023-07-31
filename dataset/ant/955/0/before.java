class PlaceHold {
  public static Native2AsciiAdapter getAdapter(String choice, ProjectComponent log)
      throws BuildException {
    if ((JavaEnvUtils.isKaffe() && (choice == null)) || IMPLEMENTATION_NAME.equals(choice)) {
      return new KaffeNative2Ascii();
    } else if (IMPLEMENTATION_NAME.equals(choice)) {
      return new SunNative2Ascii();
    } else if (choice != null) {
      return resolveClassName(choice);
    }
    return new SunNative2Ascii();
  }
}
