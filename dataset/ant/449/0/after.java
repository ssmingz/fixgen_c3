class PlaceHold {
  private void printElementDecl(PrintWriter out, String name, Class element) throws BuildException {
    if (visited.containsKey(name)) {
      return;
    }
    visited.put(name, "");
    IntrospectionHelper ih = null;
    try {
      ih = IntrospectionHelper.getHelper(getProject(), element);
    } catch (Throwable t) {
      return;
    }
    StringBuffer sb = new StringBuffer("<!ELEMENT ");
    sb.append(name).append(" ");
    if (Reference.class.equals(element)) {
      sb.append("EMPTY>").append(lSep);
      sb.append("<!ATTLIST ").append(name);
      sb.append(lSep).append("          id ID #IMPLIED");
      sb.append(lSep).append("          refid IDREF #IMPLIED");
      sb.append(">").append(lSep);
      out.println(sb);
      return;
    }
    Vector v = new Vector();
    if (ih.supportsCharacters()) {
      v.addElement("#PCDATA");
    }
    if (TaskContainer.class.isAssignableFrom(element)) {
      v.addElement(TASKS);
    }
    Enumeration e = ih.getNestedElements();
    while (e.hasMoreElements()) {
      v.addElement(e.nextElement());
    }
    if (v.isEmpty()) {
      sb.append("EMPTY");
    } else {
      sb.append("(");
      final int count = v.size();
      for (int i = 0; i < count; i++) {
        if (i != 0) {
          sb.append(" | ");
        }
        sb.append(v.elementAt(i));
      }
      sb.append(")");
      if ((count > 1) || (!v.elementAt(0).equals("#PCDATA"))) {
        sb.append("*");
      }
    }
    sb.append(">");
    out.println(sb);
    sb = new StringBuffer("<!ATTLIST ");
    sb.append(name);
    sb.append(lSep).append("          id ID #IMPLIED");
    e = ih.getAttributes();
    while (e.hasMoreElements()) {
      String attrName = ((String) (e.nextElement()));
      if ("id".equals(attrName)) {
        continue;
      }
      sb.append(lSep).append("          ").append(attrName).append(" ");
      Class type = ih.getAttributeType(attrName);
      if (type.equals(Boolean.class) || type.equals(Boolean.TYPE)) {
        sb.append(BOOLEAN).append(" ");
      } else if (Reference.class.isAssignableFrom(type)) {
        sb.append("IDREF ");
      } else if (EnumeratedAttribute.class.isAssignableFrom(type)) {
        try {
          EnumeratedAttribute ea = ((EnumeratedAttribute) (type.newInstance()));
          String[] values = ea.getValues();
          if (((values == null) || (values.length == 0)) || (!areNmtokens(values))) {
            sb.append("CDATA ");
          } else {
            sb.append("(");
            for (int i = 0; i < values.length; i++) {
              if (i != 0) {
                sb.append(" | ");
              }
              sb.append(values[i]);
            }
            sb.append(") ");
          }
        } catch (InstantiationException ie) {
          sb.append("CDATA ");
        } catch (IllegalAccessException ie) {
          sb.append("CDATA ");
        }
      } else {
        sb.append("CDATA ");
      }
      sb.append("#IMPLIED");
    }
    sb.append(">").append(lSep);
    out.println(sb);
    final int count = v.size();
    for (int i = 0; i < count; i++) {
      String nestedName = ((String) (v.elementAt(i)));
      if (((!"#PCDATA".equals(nestedName)) && (!TASKS.equals(nestedName)))
          && (!TYPES.equals(nestedName))) {
        printElementDecl(out, nestedName, ih.getElementType(nestedName));
      }
    }
  }
}
