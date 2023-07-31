class AccessibleObject {
  AccessibleObject(
      int type, int widget, Accessible accessible, int parentType, boolean isLightweight) {
    super();
    handle = ATK.g_object_new(type, 0);
    this.parentType = parentType;
    ATK.atk_object_initialize(handle, widget);
    this.accessible = accessible;
    this.isLightweight = isLightweight;
    AccessibleObjects.put(new LONG(handle), this);
    if (DEBUG) {
      System.out.println("new AccessibleObject: " + handle);
    }
  }
}
