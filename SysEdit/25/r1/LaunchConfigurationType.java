/*******************************************************************************
 * Copyright (c) 2000, 2006 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.debug.internal.core;

 
import com.ibm.icu.text.MessageFormat;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;
import org.eclipse.core.resources.IContainer;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.PlatformObject;
import org.eclipse.core.runtime.Status;
import org.eclipse.debug.core.DebugPlugin;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.ILaunchConfigurationMigrationDelegate;
import org.eclipse.debug.core.ILaunchConfigurationType;
import org.eclipse.debug.core.ILaunchConfigurationWorkingCopy;
import org.eclipse.debug.core.ILaunchManager;
import org.eclipse.debug.core.model.ILaunchConfigurationDelegate;
import org.eclipse.debug.core.sourcelookup.ISourcePathComputer;

/**
 * A launch configuration type wrappers a configuration
 * element for a <code>launchConfigurationType</code>
 * extension.
 */
public class LaunchConfigurationType extends PlatformObject implements ILaunchConfigurationType {
	
	private static String MIGRATION_DELEGATE = "migrationDelegate";  //$NON-NLS-1$
	
	/**
	 * The configuration element of the extension.
	 */
	private IConfigurationElement fElement;
	
	/**
	 * Base modes this type supports.
	 */
	private Set fBaseModes;
	
	/**
	 * Modes that delegates have been contributed for
	 */
	private Set fContributedModes;
	
	/**
	 * The delegates for launch configurations of this type.
	 * Delegates are instantiated lazily as required. There may
	 * be different delegates for different modes (since 3.0).
	 * Map of mode -> delegate
	 */
	private Map fDelegates;
	
	/**
	 * Constructs a new launch configuration type on the
	 * given configuration element.
	 * 
	 * @param element configuration element
	 */
	protected LaunchConfigurationType(IConfigurationElement element) {
		setConfigurationElement(element);
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.debug.core.ILaunchConfigurationType#getAttribute(java.lang.String)
	 */
	public String getAttribute(String attributeName) {
		return getConfigurationElement().getAttribute(attributeName);
	}
	
	/**
	 * Returns the set of modes specified in the configuration data.
	 * 
	 * @return the set of modes specified in the configuration data
	 */
	protected Set getBaseModes() {
		if (fBaseModes == null) {
			String modes= getConfigurationElement().getAttribute("modes"); //$NON-NLS-1$
			if (modes == null) {
				return new HashSet(0);
			}
			StringTokenizer tokenizer= new StringTokenizer(modes, ","); //$NON-NLS-1$
			fBaseModes = new HashSet(tokenizer.countTokens());
			while (tokenizer.hasMoreTokens()) {
				fBaseModes.add(tokenizer.nextToken().trim());
			}
		}
		return fBaseModes;
	}	

	/* (non-Javadoc)
	 * @see org.eclipse.debug.core.ILaunchConfigurationType#getCategory()
	 */
	public String getCategory() {
		return getConfigurationElement().getAttribute("category"); //$NON-NLS-1$
	}

	/**
	 * Returns this type's configuration element.
	 * 
	 * @return this type's configuration element
	 */
	protected IConfigurationElement getConfigurationElement() {
		return fElement;
	}
	
	/**
	 * Returns the set of modes delegates have been contributed for
	 * 
	 * @return the set of modes delegates have been contributed for
	 */
	protected Set getContributedModes() {
		if (fContributedModes == null) {
			fContributedModes = new HashSet(0);
			// add modes for contributed delegates
			List delegates = ((LaunchManager)DebugPlugin.getDefault().getLaunchManager()).getContributedDelegates();
			Iterator iterator = delegates.iterator();
			while (iterator.hasNext()) {
				ContributedDelegate delegate = (ContributedDelegate)iterator.next();
				if (delegate.getLaunchConfigurationType().equals(getIdentifier())) {
					fContributedModes.addAll(delegate.getModes());
				}
			}
		}
		return fContributedModes;
	}
	
	/**
	 * Returns the launch configuration delegate for launch
	 * configurations of this type. The first time this method
	 * is called, the delegate is instantiated.
	 * 
	 * @see org.eclipse.debug.core.ILaunchConfigurationType#getDelegate()
	 * @return launch configuration delegate
	 * @exception CoreException if unable to instantiate the
	 *  delegate
	 * @deprecated use <code>getDelegate(String)</code> to specify mode
	 */
	public ILaunchConfigurationDelegate getDelegate() throws CoreException {
		return getDelegate(ILaunchManager.RUN_MODE);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.debug.core.ILaunchConfigurationType#getDelegate(java.lang.String)
	 */
	public ILaunchConfigurationDelegate getDelegate(String mode) throws CoreException {
		if (!supportsMode(mode)) {
			throw new CoreException(new Status(IStatus.ERROR, DebugPlugin.getUniqueIdentifier(), DebugPlugin.INTERNAL_ERROR, MessageFormat.format(DebugCoreMessages.LaunchConfigurationType_9, new String[] {mode, getIdentifier()}), null));  
		}
		if (fDelegates == null) {
			// initialize delegate table with base modes
			fDelegates = new Hashtable(3);
		}
		ILaunchConfigurationDelegate delegate = (ILaunchConfigurationDelegate)fDelegates.get(mode);
		if (delegate == null) {
			Set modes = getBaseModes();
			if (modes.contains(mode)) {
				Object object = getConfigurationElement().createExecutableExtension("delegate"); //$NON-NLS-1$
				if (object instanceof ILaunchConfigurationDelegate) {
					Iterator iter = modes.iterator();
					while (iter.hasNext()) {
						fDelegates.put(iter.next(), object);
					}
					return (ILaunchConfigurationDelegate)object;
				} 
				throw new CoreException(new Status(IStatus.ERROR, DebugPlugin.getUniqueIdentifier(), DebugPlugin.INTERNAL_ERROR, MessageFormat.format(DebugCoreMessages.LaunchConfigurationType_Launch_delegate_for__0__does_not_implement_required_interface_ILaunchConfigurationDelegate__1, new String[]{getName()}), null)); 
			} 
			// contributed modes
			List contributed = ((LaunchManager)DebugPlugin.getDefault().getLaunchManager()).getContributedDelegates();
			Iterator iterator = contributed.iterator();
			while (iterator.hasNext()) {
				ContributedDelegate contributedDelegate = (ContributedDelegate)iterator.next();
				if (getIdentifier().equals(contributedDelegate.getLaunchConfigurationType())) {
					modes = contributedDelegate.getModes();
					if (modes.contains(mode)) {
						delegate = contributedDelegate.getDelegate();
						Iterator modesIterator = modes.iterator();
						while (modesIterator.hasNext()) {
							fDelegates.put(modesIterator.next(), delegate); 
						}
						return delegate;
					}
				}
			}
		} else {
			return delegate;
		}
		throw new CoreException(new Status(IStatus.ERROR, DebugPlugin.getUniqueIdentifier(), DebugPlugin.INTERNAL_ERROR, MessageFormat.format(DebugCoreMessages.LaunchConfigurationType_10, new String[] {getIdentifier(), mode}), null)); 
	}

	/* (non-Javadoc)
	 * @see org.eclipse.debug.core.ILaunchConfigurationType#getIdentifier()
	 */
	public String getIdentifier() {
		return getConfigurationElement().getAttribute("id"); //$NON-NLS-1$
	}

	/* (non-Javadoc)
	 * @see org.eclipse.debug.core.ILaunchConfigurationType#getName()
	 */
	public String getName() {
		return getConfigurationElement().getAttribute("name"); //$NON-NLS-1$
	}

	/* (non-Javadoc)
	 * @see org.eclipse.debug.core.ILaunchConfigurationType#getPluginId()
	 */
	public String getPluginIdentifier() {
		return fElement.getContributor().getName();
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.debug.core.ILaunchConfigurationType#getSourceLocatorId()
	 */
	public String getSourceLocatorId() {
		String id = getAttribute("sourceLocatorId"); //$NON-NLS-1$
		if (id == null) {
			// check for specification by mode specific delegate
			List delegates = ((LaunchManager)DebugPlugin.getDefault().getLaunchManager()).getContributedDelegates();
			Iterator iterator = delegates.iterator();
			while (iterator.hasNext() && id == null) {
				ContributedDelegate delegate = (ContributedDelegate)iterator.next();
				if (delegate.getLaunchConfigurationType().equals(getIdentifier())) {
					id = delegate.getSourceLocaterId();
				}
			}
		}
		return id;
	}	

	/* (non-Javadoc)
	 * @see org.eclipse.debug.core.ILaunchConfigurationType#getSourcePathComputer()
	 */
	public ISourcePathComputer getSourcePathComputer() {
		String id = getConfigurationElement().getAttribute("sourcePathComputerId"); //$NON-NLS-1$
		if (id == null) {
			// check for specification by mode specific delegate
			List delegates = ((LaunchManager)DebugPlugin.getDefault().getLaunchManager()).getContributedDelegates();
			Iterator iterator = delegates.iterator();
			while (iterator.hasNext() && id == null) {
				ContributedDelegate delegate = (ContributedDelegate)iterator.next();
				if (delegate.getLaunchConfigurationType().equals(getIdentifier())) {
					id = delegate.getSourcePathComputerId();
				}
			}
		}
		if (id != null && id.length() > 0) {
			return DebugPlugin.getDefault().getLaunchManager().getSourcePathComputer(id);
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.debug.core.ILaunchConfigurationType#getSupportedModes()
	 */
	public Set getSupportedModes() {
		HashSet modes = new HashSet(getBaseModes());
		modes.addAll(getContributedModes());
		return modes;
	}

	/**
	 * determines if the specified candidate is suitable for migration by loading it delegate.
	 * @param candidate the candidate to inspect for migration suitability
	 * @return true if the specified launch configuration is suitable for migration, false otherwise
	 * @throws CoreException
	 * 
	 * @since 3.2
	 */
	public boolean isMigrationCandidate(ILaunchConfiguration candidate) throws CoreException {
		if(getAttribute(MIGRATION_DELEGATE) != null) {
			if(fDelegates == null) {
				fDelegates = new Hashtable();
			}
			Object delegate = fDelegates.get(MIGRATION_DELEGATE);
			if(delegate == null) {
				delegate = getConfigurationElement().createExecutableExtension(MIGRATION_DELEGATE);
				fDelegates.put(MIGRATION_DELEGATE, delegate);
			}
			if(delegate instanceof ILaunchConfigurationMigrationDelegate) {
				return ((ILaunchConfigurationMigrationDelegate)delegate).isCandidate(candidate);
			}
		}
		return false;
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.debug.core.ILaunchConfigurationType#isPublic()
	 */
	public boolean isPublic() {
		String publicString = getConfigurationElement().getAttribute("public"); //$NON-NLS-1$
		if (publicString != null) {
			if (publicString.equalsIgnoreCase("false")) { //$NON-NLS-1$
				return false;
			}
		} 
		return true;
	}

	/**
	 * migrates the specified launch configuration by loading its delegate
	 * @param candidate the candidate launch configuration to migrate
	 * @throws CoreException
	 * 
	 * @since 3.2
	 */
	public void migrate(ILaunchConfiguration candidate) throws CoreException {
		if(getAttribute(MIGRATION_DELEGATE) != null) { 
			if(fDelegates == null) {
				fDelegates = new Hashtable();
			}
			Object delegate = fDelegates.get(MIGRATION_DELEGATE);
			if(delegate == null) {
				delegate = getConfigurationElement().createExecutableExtension(MIGRATION_DELEGATE);
				fDelegates.put(MIGRATION_DELEGATE, delegate);
			}
			if(delegate instanceof ILaunchConfigurationMigrationDelegate) {
				((ILaunchConfigurationMigrationDelegate)delegate).migrate(candidate);
			}
		}
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.debug.core.ILaunchConfigurationType#newInstance(org.eclipse.core.resources.IContainer, java.lang.String)
	 */
	public ILaunchConfigurationWorkingCopy newInstance(IContainer container, String name) {
			return new LaunchConfigurationWorkingCopy(container, name, this);
	}

	/**
	 * Sets this type's configuration element.
	 * 
	 * @param element this type's configuration element
	 */
	private void setConfigurationElement(IConfigurationElement element) {
		fElement = element;
	}

	/**
	 * @see ILaunchConfigurationType#supportsMode(String)
	 */
	public boolean supportsMode(String mode) {
		return getBaseModes().contains(mode) || getContributedModes().contains(mode);
	}
}

