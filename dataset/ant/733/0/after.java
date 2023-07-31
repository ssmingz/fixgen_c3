class PlaceHold {
  private Method findMatchingMethod(Class paramClass, List methods) {
    if (paramClass == null) {
      return null;
    }
    Class matchedClass = null;
    Method matchedMethod = null;
    for (int i = 0; i < methods.size(); ++i) {
      Method method = ((Method) (methods.get(i)));
      Class methodClass = method.getParameterTypes()[0];
      if (methodClass.isAssignableFrom(paramClass)) {
        if (matchedClass == null) {
          matchedClass = methodClass;
          matchedMethod = method;
        } else if (!methodClass.isAssignableFrom(matchedClass)) {
          throw new BuildException(
              (((("ambiguous: types " + matchedClass.getName()) + " and ") + methodClass.getName())
                      + " match ")
                  + paramClass.getName());
        }
      }
    }
    return matchedMethod;
  }
}
