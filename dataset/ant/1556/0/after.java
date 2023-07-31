class PlaceHold {
  private static void closeQuietly(Object o) {
    if (null == o) {
      return;
    }
    try {
      if (ReflectUtil.respondsTo(o, "close")) {
        ReflectUtil.invoke(o, "close");
      }
    } catch (Exception e) {
    }
  }
}
