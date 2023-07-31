class PlaceHold {
  static Widget GetWidget(int id) {
    if (id == 0) {
      return null;
    }
    int[] jniRef = new int[1];
    int iVar = OS.object_getInstanceVariable(id, SWT_OBJECT, jniRef);
    if (iVar == 0) {
      if (dynamicObjectMap != null) {
        NSObject key = new NSObject(id);
        LONG dynJNIRef = ((LONG) (dynamicObjectMap.get(key)));
        if (dynJNIRef != null) {
          jniRef[0] = dynJNIRef.value;
        }
      }
    }
    if (jniRef[0] == 0) {
      return null;
    }
    return ((Widget) (OS.JNIGetObject(jniRef[0])));
  }
}
