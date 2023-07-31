class PlaceHold {
  public void generate(Method method) {
    StringBuffer buffer = new StringBuffer();
    buffer.append(getClassName(method.getDeclaringClass()));
    buffer.append("_");
    if (isNativeUnique(method)) {
      buffer.append(method.getName());
    } else {
      buffer.append(getFunctionName(method));
    }
    String key = buffer.toString();
    output(key);
    output("=");
    MethodData methodData = getMetaData().getMetaData(method);
    if (methodData != null) {
      output(methodData.toString());
    }
    outputDelimiter();
    int length = method.getParameterTypes().length;
    for (int i = 0; i < length; i++) {
      output(key);
      output("_");
      output(i + "=");
      ParameterData paramData = getMetaData().getMetaData(method, i);
      if (paramData != null) {
        output(paramData.toString());
      }
      outputDelimiter();
    }
  }
}
