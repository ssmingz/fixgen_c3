class PlaceHold {
  private UnknownElement copy(UnknownElement ue) {
    UnknownElement ret = new UnknownElement(ue.getTag());
    ret.setNamespace(ue.getNamespace());
    ret.setProject(getProject());
    ret.setQName(ue.getQName());
    ret.setTaskName(ue.getTaskName());
    ret.setLocation(ue.getLocation());
    if (getOwningTarget() == null) {
      Target t = new Target();
      t.setProject(getProject());
      ret.setOwningTarget(t);
    } else {
      ret.setOwningTarget(getOwningTarget());
    }
    RuntimeConfigurable rc = new RuntimeConfigurable(ret, ue.getTaskName());
    rc.setPolyType(ue.getWrapper().getPolyType());
    Map map = ue.getWrapper().getAttributeMap();
    for (Iterator i = map.entrySet().iterator(); i.hasNext(); ) {
      Map.Entry entry = ((Map.Entry) (i.next()));
      rc.setAttribute(
          ((String) (entry.getKey())), macroSubs(((String) (entry.getValue())), localProperties));
    }
    rc.addText(macroSubs(ue.getWrapper().getText().toString(), localProperties));
    Enumeration e = ue.getWrapper().getChildren();
    while (e.hasMoreElements()) {
      RuntimeConfigurable r = ((RuntimeConfigurable) (e.nextElement()));
      UnknownElement unknownElement = ((UnknownElement) (r.getProxy()));
      String tag = unknownElement.getTaskType().toLowerCase(Locale.US);
      MacroDef.TemplateElement templateElement =
          ((MacroDef.TemplateElement) (getNsElements().get(tag)));
      if (templateElement == null) {
        UnknownElement child = copy(unknownElement);
        rc.addChild(child.getWrapper());
        ret.addChild(child);
      } else {
        Element element = ((Element) (presentElements.get(tag)));
        if (element == null) {
          if (!templateElement.isOptional()) {
            throw new BuildException(
                ("Required nested element " + templateElement.getName()) + " missing");
          }
          continue;
        }
        for (Iterator i = element.getUnknownElements().iterator(); i.hasNext(); ) {
          UnknownElement child = ((UnknownElement) (i.next()));
          rc.addChild(child.getWrapper());
          ret.addChild(child);
        }
      }
    }
    return ret;
  }
}
