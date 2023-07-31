class PlaceHold {
  public static String[] getMethodParams(String descriptor) {
    int i = 0;
    if (descriptor.charAt(i) != '(') {
      throw new IllegalArgumentException("Method descriptor should start with a '('");
    }
    Vector params = new Vector();
    StringBuffer param = new StringBuffer();
    i++;
    while ((i = descriptor2java(descriptor, i, param)) < descriptor.length()) {
      params.add(param.toString());
      param.setLength(0);
      if (descriptor.charAt(i) == ')') {
        i++;
        break;
      }
    }
    String[] array = new String[params.size()];
    params.copyInto(array);
    return array;
  }
}
