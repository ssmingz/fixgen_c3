class PlaceHold {
  private UnknownElement copy(UnknownElement ue, boolean nested) {
    UnknownElement ret = new UnknownElement(ue.getTag());
    ret.setNamespace(ue.getNamespace());
    ret.setProject(getProject());
    ret.setQName(ue.getQName());
    ret.setTaskType(ue.getTaskType());
    ret.setTaskName(ue.getTaskName());
    ret.setLocation(macroDef.getBackTrace() ? ue.getLocation() : getLocation());
    if (getOwningTarget() == null) {
      Target t = new Target();
      t.setProject(getProject());
      ret.setOwningTarget(t);
    } else {
      ret.setOwningTarget(getOwningTarget());
    }
    RuntimeConfigurable rc = new RuntimeConfigurable(ret, ue.getTaskName());
    rc.setPolyType(ue.getWrapper().getPolyType());
    Map m = ue.getWrapper().getAttributeMap();
    for (Iterator i = m.entrySet().iterator(); i.hasNext(); ) {
      Map.Entry entry = ((Map.Entry) (i.next()));
      rc.setAttribute(
          ((String) (entry.getKey())), macroSubs(((String) (entry.getValue())), localAttributes));
    }
    rc.addText(macroSubs(ue.getWrapper().getText().toString(), localAttributes));
    Enumeration e = ue.getWrapper().getChildren();
    while (e.hasMoreElements()) {
      RuntimeConfigurable r = ((RuntimeConfigurable) (e.nextElement()));
      UnknownElement unknownElement = ((UnknownElement) (r.getProxy()));
      String tag = unknownElement.getTaskType();
      if (tag != null) {
        tag = tag.toLowerCase(Locale.US);
      }
      MacroDef.TemplateElement templateElement =
          ((MacroDef.TemplateElement) (getNsElements().get(tag)));
      if ((templateElement == null) || nested) {
        UnknownElement child = copy(unknownElement, nested);
        rc.addChild(child.getWrapper());
        ret.addChild(child);
      } else if (templateElement.isImplicit()) {
        if ((unknownElements.size() == 0) && (!templateElement.isOptional())) {
          throw new BuildException(
              "Missing nested elements for implicit element " + templateElement.getName());
        }
        for (Iterator i = unknownElements.iterator(); i.hasNext(); ) {
          UnknownElement child = copy(((UnknownElement) (i.next())), true);
          rc.addChild(child.getWrapper());
          ret.addChild(child);
        }
      } else {
        UnknownElement presentElement = ((UnknownElement) (presentElements.get(tag)));
        if (presentElement == null) {
          if (!templateElement.isOptional()) {
            throw new BuildException(
                ("Required nested element " + templateElement.getName()) + " missing");
          }
          continue;
        }
        String presentText = presentElement.getWrapper().getText().toString();
        if (!"".equals(presentText)) {
          rc.addText(macroSubs(presentText, localAttributes));
        }
        List list = presentElement.getChildren();
        if (list != null) {
          for (Iterator i = list.iterator(); i.hasNext(); ) {
            UnknownElement child = copy(((UnknownElement) (i.next())), true);
            rc.addChild(child.getWrapper());
            ret.addChild(child);
          }
        }
      }
    }
    return ret;
  }
}
