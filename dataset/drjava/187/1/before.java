class PlaceHold {
  public boolean isAvailable() {
    try {
      Class<?> diagnostic = Class.forName("javax.tools.Diagnostic");
      diagnostic.getMethod("getKind");
      return true;
    } catch (Exception e) {
      return false;
    } catch (LinkageError e) {
      return false;
    }
  }
}
