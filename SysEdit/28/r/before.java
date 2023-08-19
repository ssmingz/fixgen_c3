class LaunchConfigurationWorkingCopy {
  protected LaunchConfigurationWorkingCopy(
      org.eclipse.core.resources.IContainer container,
      String name,
      org.eclipse.debug.core.ILaunchConfigurationType type) {
    super(((org.eclipse.core.runtime.IPath) (null)));
    setName(name);
    setInfo(new org.eclipse.debug.internal.core.LaunchConfigurationInfo());
    getInfo().setType(type);
    setContainer(container);
    fSuppressChange = false;
  }
}
