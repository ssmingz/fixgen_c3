class PlaceHold {
  private static void closeQuietly(Object o) {
    try {
      if (ReflectUtil.respondsTo(o, "close")) {
        ReflectUtil.invoke(o, "close");
      }
    } catch (Exception e) {
    }
  }
}
