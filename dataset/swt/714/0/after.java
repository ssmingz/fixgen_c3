class PlaceHold {
  static String getFunctionName(Method method, Class[] paramTypes) {
    String function = toC(method.getName());
    if (!isNativeUnique(method)) {
      StringBuffer buffer = new StringBuffer();
      buffer.append(function);
      buffer.append("__");
      if (paramTypes.length > 0) {
        for (int i = 0; i < paramTypes.length; i++) {
          Class paramType = paramTypes[i];
          buffer.append(toC(getTypeSignature(paramType)));
        }
      }
      return buffer.toString();
    }
    return function;
  }
}
