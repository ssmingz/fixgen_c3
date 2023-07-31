class PlaceHold {
  private UnknownElement getReplacement() {
    if (replacement == null) {
      replacement = new UnknownElement(taskType);
      replacement.setProject(getProject());
      replacement.setTaskType(taskType);
      replacement.setTaskName(taskName);
      replacement.setLocation(getLocation());
      replacement.setOwningTarget(target);
      replacement.setRuntimeConfigurableWrapper(wrapper);
      wrapper.setProxy(replacement);
      replaceChildren(wrapper, replacement);
      target.replaceChild(this, replacement);
      replacement.maybeConfigure();
    }
    return replacement;
  }
}
