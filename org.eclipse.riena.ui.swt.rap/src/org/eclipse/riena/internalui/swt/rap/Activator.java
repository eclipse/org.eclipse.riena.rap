/*******************************************************************************
 * Copyright (c) 2007, 2010 compeople AG and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    compeople AG - initial API and implementation
 *******************************************************************************/
package org.eclipse.riena.internalui.swt.rap;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.osgi.framework.BundleContext;

import org.eclipse.core.runtime.jobs.IJobChangeEvent;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.core.runtime.jobs.JobChangeAdapter;
import org.eclipse.rap.ui.internal.progress.JobManagerAdapter;
import org.eclipse.rwt.internal.lifecycle.RWTLifeCycle;
import org.eclipse.rwt.internal.lifecycle.UICallBackServiceHandler;
import org.eclipse.rwt.internal.service.ContextProvider;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.progress.UIJob;

import org.eclipse.riena.ui.swt.AbstractRienaUIPlugin;

/**
 * The activator class controls the plug-in life cycle
 */
public class Activator extends AbstractRienaUIPlugin {

	// The plug-in ID
	public static final String PLUGIN_ID = "org.eclipse.riena.ui.swt.rap"; //$NON-NLS-1$

	// The shared instance
	private static Activator plugin;

	private UICallbackActivationListener uiCallbackListener;

	@Override
	public void start(final BundleContext context) throws Exception {
		super.start(context);
		plugin = this;
		removeRAPJobChangeListener();
		registerJobChangeListener();
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void removeRAPJobChangeListener() {
		Job.getJobManager().removeJobChangeListener(JobManagerAdapter.getInstance());
	}

	private void registerJobChangeListener() {
		uiCallbackListener = new UICallbackActivationListener();
		Job.getJobManager().addJobChangeListener(uiCallbackListener);
	}

	@Override
	public void stop(final BundleContext context) throws Exception {
		deregisterJobChangeListener();
		Activator.plugin = null;
		super.stop(context);
	}

	private void deregisterJobChangeListener() {
		Job.getJobManager().removeJobChangeListener(uiCallbackListener);
		uiCallbackListener = null;
	}

	/**
	 * Returns the shared instance
	 */
	public static Activator getDefault() {
		return plugin;
	}

	private final static class UICallbackActivationListener extends JobChangeAdapter {
		private final Map<Job, Display> job2Display = new ConcurrentHashMap<Job, Display>();

		@Override
		public void scheduled(final IJobChangeEvent event) {
			final Display display = findDisplay(event.getJob());
			if (display != null && !display.isDisposed()) {
				job2Display.put(event.getJob(), display);
				display.asyncExec(new Runnable() {
					public void run() {
						final String id = String.valueOf(event.getJob().hashCode());
						UICallBackServiceHandler.activateUICallBacksFor(id);
					}
				});
			}
		}

		@Override
		public void done(final IJobChangeEvent event) {
			final Display display = job2Display.get(event.getJob());
			if (display != null && !display.isDisposed()) {
				try {
					display.asyncExec(new Runnable() {
						public void run() {
							final Job job = event.getJob();
							final String id = String.valueOf(job.hashCode());
							UICallBackServiceHandler.deactivateUICallBacksFor(id);
						}
					});
				} finally {
					job2Display.remove(event.getJob());
				}
			}
		}

		private static Display findDisplay(final Job job) {
			Display result = null;
			if (ContextProvider.hasContext()) {
				result = RWTLifeCycle.getSessionDisplay();
			} else {
				if (job instanceof UIJob) {
					final UIJob uiJob = (UIJob) job;
					result = uiJob.getDisplay();
					if (result == null) {
						final String msg = "UIJob " + uiJob.getName()
								+ " cannot be scheduled without an associated display.";
						throw new IllegalStateException(msg);
					}
				}
			}
			return result;
		}
	}

}