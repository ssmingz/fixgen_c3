class PlaceHold {
  public JNIClass[] getStructureClasses(JNIClass[] classes) {
    if (mainClass == null) {
      return new JNIClass[0];
    }
    ArrayList result = new ArrayList();
    outer:
    for (int i = 0; i < classes.length; i++) {
      JNIClass clazz = classes[i];
      JNIMethod[] methods = clazz.getDeclaredMethods();
      for (int j = 0; j < methods.length; j++) {
        JNIMethod method = methods[j];
        int mods = method.getModifiers();
        if ((mods & Modifier.NATIVE) != 0) {
          continue outer;
        }
      }
      JNIField[] fields = clazz.getDeclaredFields();
      boolean hasPublicFields = false;
      for (int j = 0; j < fields.length; j++) {
        JNIField field = fields[j];
        int mods = field.getModifiers();
        if (((mods & Modifier.PUBLIC) != 0) && ((mods & Modifier.STATIC) == 0)) {
          hasPublicFields = true;
          break;
        }
      }
      if (!hasPublicFields) {
        continue;
      }
      result.add(clazz);
    }
    return ((JNIClass[]) (result.toArray(new JNIClass[result.size()])));
  }
}
