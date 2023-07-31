class PlaceHold {
  private static void processNestedProperties(Project project, Object target, Element targetElement)
      throws BuildException {
    Class targetClass = target.getClass();
    NodeList list = targetElement.getChildNodes();
    for (int i = 0; i < list.getLength(); i++) {
      Node node = list.item(i);
      if (node.getNodeType() == Node.TEXT_NODE) {
        String text = ((Text) (node)).getData();
        try {
          Method addProp = targetClass.getMethod("addText", new Class[] {"".getClass()});
          Object child = addProp.invoke(target, new Object[] {text});
        } catch (NoSuchMethodException nsme) {
          if (text.trim().length() > 0) {
            throw new BuildException(targetClass + " does not support nested text elements");
          }
        } catch (InvocationTargetException ite) {
          throw new BuildException(ite.getMessage());
        } catch (IllegalAccessException iae) {
          throw new BuildException(iae.getMessage());
        }
      }
      if (node.getNodeType() == Node.ELEMENT_NODE) {
        Element element = ((Element) (node));
        String propType = element.getTagName();
        String methodName =
            ("create" + Character.toUpperCase(propType.charAt(0))) + propType.substring(1);
        try {
          Method addProp = targetClass.getMethod(methodName, new Class[] {});
          Object child = addProp.invoke(target, new Object[] {});
          configure(project, child, element);
          processNestedProperties(project, child, element);
        } catch (NoSuchMethodException nsme) {
          throw new BuildException(
              ((targetClass + " does not support nested ") + propType) + " properties");
        } catch (InvocationTargetException ite) {
          throw new BuildException(ite.getMessage());
        } catch (IllegalAccessException iae) {
          throw new BuildException(iae.getMessage());
        }
      }
    }
  }
}
