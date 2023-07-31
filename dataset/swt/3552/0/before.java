class AccessibleObject {
  AccessibleObject(
      int type, int widget, Accessible accessible, int parentType, boolean isLightweight) {
    super();
    handle = OS.g_object_new(type, 0);
    OS.atk_object_initialize(handle, widget);
    this.accessible = accessible;
    this.isLightweight = isLightweight;
    this.parentType = parentType;
    if (DEBUG) {
      System.out.println("new AccessibleObject: " + handle);
    }
  }
}
