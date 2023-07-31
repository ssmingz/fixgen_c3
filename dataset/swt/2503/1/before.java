class PlaceHold {
  Widget removeWidget(NSObject view) {
    if (view == null) {
      return null;
    }
    int[] jniRef = new int[1];
    int iVar = OS.object_getInstanceVariable(view.id, SWT_OBJECT, jniRef);
    if (iVar == 0) {
      if (dynamicObjectMap != null) {
        Long dynJNIRef = ((Long) (dynamicObjectMap.get(view)));
        if (dynJNIRef != null) {
          jniRef[0] = ((int) (dynJNIRef.longValue()));
        }
        dynamicObjectMap.remove(view);
      }
    }
    if (jniRef[0] == 0) {
      return null;
    }
    Widget widget = ((Widget) (OS.JNIGetObject(jniRef[0])));
    OS.object_setInstanceVariable(view.id, SWT_OBJECT, 0);
    return widget;
  }
}
