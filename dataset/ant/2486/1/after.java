class PlaceHold {
  public static String getDefault() {
    if (JavaEnvUtils.isKaffe() || JavaEnvUtils.isClasspathBased()) {
      return KaffeNative2Ascii.IMPLEMENTATION_NAME;
    }
    return SunNative2Ascii.IMPLEMENTATION_NAME;
  }
}
