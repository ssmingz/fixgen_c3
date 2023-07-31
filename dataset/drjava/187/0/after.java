class PlaceHold {
  public boolean isAvailable() {
    try {
      Class<?> diagnostic = Class.forName("javax.tools.Diagnostic");
      diagnostic.getMethod("getKind");
      Class.forName("javax.lang.model.element.Name");
      Class.forName("com.sun.tools.javac.main.JavaCompiler");
      return true;
    } catch (Exception e) {
      return false;
    } catch (LinkageError e) {
      return false;
    }
  }
}
