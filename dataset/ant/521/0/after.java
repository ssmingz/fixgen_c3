class PlaceHold {
  private static void configureTasks(Project project, Target target, Element targetElement)
      throws BuildException {
    NodeList list = targetElement.getChildNodes();
    for (int i = 0; i < list.getLength(); i++) {
      Node node = list.item(i);
      if (node.getNodeType() == Node.ELEMENT_NODE) {
        Element element = ((Element) (node));
        String taskType = element.getTagName();
        Task task = project.createTask(taskType);
        configure(project, task, element);
        task.init();
        task.setTarget(target);
        target.addTask(task);
        processNestedProperties(project, task, element);
      }
    }
  }
}
