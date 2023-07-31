class PlaceHold {
  public final void setProjectReference(final Object obj) {
    if (obj instanceof ProjectComponent) {
      ((ProjectComponent) (obj)).setProject(this);
      return;
    }
    try {
      final Method method = obj.getClass().getMethod("setProject", new Class[] {Project.class});
      if (method != null) {
        method.invoke(obj, new Object[] {this});
      }
    } catch (final Throwable e) {
    }
  }
}
